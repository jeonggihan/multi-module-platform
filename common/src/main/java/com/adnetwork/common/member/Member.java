package com.adnetwork.common.member;

import jakarta.persistence.*;
import com.adnetwork.common.base.BaseAuditableEntity;
import com.adnetwork.core.variable.StatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member extends BaseAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    private String name;

    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCode used = StatusCode.USED;
}
