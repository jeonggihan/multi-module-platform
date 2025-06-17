package com.adnetwork.api.domain.exchange.service;

import com.adnetwork.api.domain.exchange.ExchangeRateRepository;
import com.adnetwork.common.exchange.ExchangeRate;
import com.adnetwork.core.variable.CurrencyCode;
import com.adnetwork.core.variable.StatusCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class KrwExchangeRateServiceImpl implements KrwExchangeRateService {

  private final ExchangeRateRepository exchangeRateRepository;

  public KrwExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
    this.exchangeRateRepository = exchangeRateRepository;
  }

  public BigDecimal getKrwTo(CurrencyCode targetCurrency) {
    return getExchangeRate(CurrencyCode.KRW, targetCurrency);
  }

  public BigDecimal getFromToKrw(CurrencyCode baseCurrency) {
    return getExchangeRate(baseCurrency, CurrencyCode.KRW);
  }

  @Override
  public BigDecimal getExchangeRate(CurrencyCode base, CurrencyCode target) {
    return exchangeRateRepository.findTopByBaseCurrencyAndTargetCurrencyAndUsedOrderByExchangeDateDesc(
            base, target, StatusCode.USED).map(ExchangeRate::getExchangeRate)
        .orElseThrow(() -> new IllegalArgumentException("환율 정보가 없습니다."));
  }
}
