package com.adnetwork.common.member;

import jakarta.persistence.*;
import com.adnetwork.common.base.BaseAuditableEntity;
import com.adnetwork.core.variable.StatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.adnetwork.core.variable.StatusCode.USED;

@Entity
@Getter
@NoArgsConstructor
public class Role extends BaseAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCode used = USED;
}
