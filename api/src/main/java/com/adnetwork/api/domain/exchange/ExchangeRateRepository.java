package com.adnetwork.api.domain.exchange;

import com.adnetwork.common.exchange.ExchangeRate;
import com.adnetwork.common.member.Member;
import com.adnetwork.core.variable.CurrencyCode;
import com.adnetwork.core.variable.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Currency;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

  Optional<ExchangeRate> findTopByBaseCurrencyAndTargetCurrencyAndUsedOrderByExchangeDateDesc(
      CurrencyCode baseCurrency, CurrencyCode targetCurrency, StatusCode used);
}