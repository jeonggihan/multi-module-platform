package com.adnetwork.common.member;

import jakarta.persistence.*;
import com.adnetwork.common.base.BaseAuditableEntity;
import com.adnetwork.core.variable.StatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ScreenPermission extends BaseAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCode canRead = StatusCode.NOT_USED;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCode canWrite = StatusCode.NOT_USED;
}
