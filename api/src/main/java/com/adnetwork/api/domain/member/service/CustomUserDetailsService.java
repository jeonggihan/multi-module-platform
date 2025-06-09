package com.adnetwork.api.domain.member.service;

import com.adnetwork.api.domain.member.dto.LoginUserDto;
import com.adnetwork.api.domain.member.dto.LoginUserPrincipal;
import com.adnetwork.api.domain.member.repository.MemberRepository;
import com.adnetwork.api.domain.member.repository.RoleRepository;
import com.adnetwork.api.domain.member.repository.ScreenPermissionRepository;
import com.adnetwork.api.exception.AuthenticationException;
import com.adnetwork.common.member.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final ScreenPermissionRepository permissionRepository;

    public CustomUserDetailsService(MemberRepository memberRepository, RoleRepository roleRepository, ScreenPermissionRepository permissionRepository) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
            .orElseThrow(() -> new AuthenticationException("존재하지 않는 사용자: " + loginId));

        List<String> roles = roleRepository.findNamesByMemberId(member.getId());
        List<LoginUserDto.ScreenPermissionDto> permissions = permissionRepository.findPermissionsByRoleIds(
                roleRepository.findIdsByMemberId(member.getId())
        );

        LoginUserDto dto = LoginUserDto.builder()
            .memberId(member.getId())
            .loginId(member.getLoginId())
            .password(member.getPassword())
            .name(member.getName())
            .phoneNumber(member.getPhoneNumber())
            .used(member.getUsed())
            .roles(roles)
            .permissions(permissions)
            .build();

        return new LoginUserPrincipal(dto);
    }
}