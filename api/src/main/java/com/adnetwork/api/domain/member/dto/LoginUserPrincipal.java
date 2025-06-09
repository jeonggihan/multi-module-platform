package com.adnetwork.api.domain.member.dto;

import com.adnetwork.core.variable.StatusCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class LoginUserPrincipal implements UserDetails {

    private final LoginUserDto loginUser;

    public LoginUserPrincipal(LoginUserDto loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return loginUser.getRoles().stream()
            .map(role -> (GrantedAuthority) () -> role)
            .toList();
    }

    @Override
    public String getPassword() {
        return loginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return loginUser.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StatusCode.USED.equals(loginUser.getUsed());
    }
}
