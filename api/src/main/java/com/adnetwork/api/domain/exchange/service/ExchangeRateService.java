package com.adnetwork.api.domain.exchange.service;

import com.adnetwork.core.variable.CurrencyCode;
import java.math.BigDecimal;

public interface ExchangeRateService {

  BigDecimal getExchangeRate(CurrencyCode base, CurrencyCode target);
}
