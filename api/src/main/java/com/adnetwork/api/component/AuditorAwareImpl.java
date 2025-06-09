package com.adnetwork.api.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {

    private static final String ANONYMOUS = "anonymousUser";
    private static final String SYSTEM = "system";

    @Override
    public Optional<String> getCurrentAuditor() {
        // 로그인 사용자 ID 리턴, 없으면 "system"
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName())
                       .filter(s -> !s.equals(ANONYMOUS))
                       .or(() -> Optional.of(SYSTEM));
    }
}
