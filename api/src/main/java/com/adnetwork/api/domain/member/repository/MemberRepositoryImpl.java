package com.adnetwork.api.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.adnetwork.common.member.Member;
import org.springframework.stereotype.Repository;

import static com.adnetwork.common.member.QMember.member;

import java.util.Optional;

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
