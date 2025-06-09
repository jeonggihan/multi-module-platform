package com.adnetwork.common.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@IdClass(MemberRoleId.class)
public class MemberRole {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @Id
    @Column(name = "role_id")
    private Long roleId;

    // 선택적으로 연관관계 맵핑 가능
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}
