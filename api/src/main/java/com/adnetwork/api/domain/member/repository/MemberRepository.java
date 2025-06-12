package com.adnetwork.api.domain.member.repository;

import com.adnetwork.common.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByLoginId(String loginId);
}