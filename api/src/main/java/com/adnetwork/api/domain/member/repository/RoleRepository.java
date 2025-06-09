package com.adnetwork.api.domain.member.repository;

import java.util.List;

public interface RoleRepository {
    List<String> findNamesByMemberId(Long memberId);
    List<Long> findIdsByMemberId(Long memberId);
}