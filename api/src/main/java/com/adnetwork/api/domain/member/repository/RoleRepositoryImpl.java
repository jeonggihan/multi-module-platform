package com.adnetwork.api.domain.member.repository;

import static com.adnetwork.common.member.QMemberRole.memberRole;
import static com.adnetwork.common.member.QRole.role;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

  private final JPAQueryFactory queryFactory;

  public RoleRepositoryImpl(JPAQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  @Override
  public List<String> findNamesByMemberId(Long memberId) {
    return queryFactory
        .select(role.name)
        .from(memberRole)
        .join(role).on(memberRole.roleId.eq(role.id))
        .where(memberRole.memberId.eq(memberId))
        .fetch();
  }

  @Override
  public List<Long> findIdsByMemberId(Long memberId) {
    return queryFactory
        .select(role.id)
        .from(memberRole)
        .join(role).on(memberRole.roleId.eq(role.id))
        .where(memberRole.memberId.eq(memberId))
        .fetch();
  }
}
