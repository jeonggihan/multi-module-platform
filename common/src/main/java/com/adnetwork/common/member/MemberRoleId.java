package com.adnetwork.common.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

// 복합키 ID 클래스
@Getter
@RequiredArgsConstructor
public class MemberRoleId implements Serializable {
    private Long memberId;
    private Long roleId;
}
