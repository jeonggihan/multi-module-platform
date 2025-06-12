package com.adnetwork.api.domain.member.repository;

import static com.adnetwork.common.member.QMember.member;

import com.adnetwork.common.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public abstract class MemberRepositoryImpl implements MemberRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public MemberRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public Optional<Member> findByLoginId(String loginId) {
    return Optional.ofNullable(
        jpaQueryFactory.selectFrom(member)
            .where(member.loginId.eq(loginId))
            .fetchOne()
    );
  }
}
