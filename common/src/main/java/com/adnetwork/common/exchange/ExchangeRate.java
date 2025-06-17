package com.adnetwork.common.exchange;

import com.adnetwork.common.base.BaseAuditableEntity;
import com.adnetwork.core.variable.CurrencyCode;
import com.adnetwork.core.variable.StatusCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rate", uniqueConstraints = @UniqueConstraint(name = "uq_exchange_rate_date", columnNames = {
    "base_currency", "target_currency", "exchange_date"}))
@Getter
@NoArgsConstructor
public class ExchangeRate extends BaseAuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 3, nullable = false)
  @Enumerated(EnumType.STRING)
  private CurrencyCode baseCurrency;

  @Column(length = 3, nullable = false)
  @Enumerated(EnumType.STRING)
  private CurrencyCode targetCurrency;

  @Column(precision = 18, scale = 8, nullable = false)
  private BigDecimal exchangeRate;

  @Column(nullable = false)
  private LocalDate exchangeDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusCode used = StatusCode.USED;
}
