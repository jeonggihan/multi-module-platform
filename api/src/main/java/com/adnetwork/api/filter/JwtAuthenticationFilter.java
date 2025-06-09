package com.adnetwork.api.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.adnetwork.api.component.JwtProvider;
import com.adnetwork.api.domain.member.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    public static final String[] WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/api/auth/**",
            "/h2-console",
            "/h2-console/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/favicon.ico"
    };

    public JwtAuthenticationFilter(JwtProvider jwtProvider, CustomUserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        AntPathMatcher matcher = new AntPathMatcher();
        return Arrays.stream(WHITELIST)
                .anyMatch(pattern -> matcher.match(pattern, uri));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null && jwtProvider.validateToken(token)) {
            Claims claims = jwtProvider.parseClaims(token);
            String loginId = claims.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(AUTH_HEADER);
        if (bearer != null && bearer.startsWith(BEARER_PREFIX)) {
            return bearer.substring(7);
        }
        return null;
    }
}
