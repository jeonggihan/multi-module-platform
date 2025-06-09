package com.adnetwork.api.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.adnetwork.api.domain.member.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.adnetwork.common.member.QScreen.screen;
import static com.adnetwork.common.member.QScreenPermission.screenPermission;

@Repository
@RequiredArgsConstructor
public class ScreenPermissionRepositoryImpl implements ScreenPermissionRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<LoginUserDto.ScreenPermissionDto> findPermissionsByRoleIds(List<Long> roleIds) {
        return queryFactory
                .select(
                        Projections.constructor(
                                LoginUserDto.ScreenPermissionDto.class,
                                screen.code,
                                screenPermission.canRead,
                                screenPermission.canWrite
                        )
                )
                .from(screenPermission)
                .join(screenPermission.screen, screen)
                .where(screenPermission.role.id.in(roleIds))
                .fetch();
    }
}
