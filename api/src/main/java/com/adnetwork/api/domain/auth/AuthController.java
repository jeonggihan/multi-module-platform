package com.adnetwork.api.domain.auth;

import com.adnetwork.api.component.JwtProvider;
import com.adnetwork.api.domain.auth.service.RefreshTokenService;
import com.adnetwork.api.domain.member.dto.LoginUserDto;
import com.adnetwork.api.domain.member.dto.LoginUserPrincipal;
import com.adnetwork.api.domain.member.service.CustomUserDetailsService;
import com.adnetwork.api.exception.AuthenticationException;
import com.adnetwork.core.variable.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, CustomUserDetailsService userDetailsService, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException(ErrorCode.UNAUTHORIZED, e.getMessage());
        }

        LoginUserPrincipal principal = (LoginUserPrincipal) userDetailsService.loadUserByUsername(request.getLoginId());
        LoginUserDto loginUser = principal.getLoginUser();

        String accessToken = jwtProvider.createToken(loginUser);
        String refreshToken = jwtProvider.createRefreshToken(loginUser.getLoginId());

        refreshTokenService.save(loginUser.getLoginId(), refreshToken); // Redis 저장

        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal LoginUserPrincipal principal) {
        refreshTokenService.delete(principal.getLoginUser().getLoginId());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String bearerToken,
                                     @RequestBody RefreshRequest request) {

        String refreshToken = request.getRefreshToken();
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
        }

        String loginId = jwtProvider.parseClaims(refreshToken).getSubject();
        String stored = refreshTokenService.get(loginId);

        if (!Objects.equals(stored, refreshToken)) {
            throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
        }

        LoginUserDto loginUser = ((LoginUserPrincipal) userDetailsService.loadUserByUsername(loginId)).getLoginUser();
        String newAccessToken = jwtProvider.createToken(loginUser);

        return ResponseEntity.ok(new LoginResponse(newAccessToken, refreshToken));
    }



    @Getter
    @Builder
    @Jacksonized
    public static class LoginRequest {
        private final String loginId;
        private final String password;
    }

    @Getter
    @Builder
    @Jacksonized
    public static class RefreshRequest {
        private final String refreshToken;
    }

    @Getter
    @Builder
    public static class LoginResponse {
        private final String accessToken;
        private final String refreshToken;
    }
}
