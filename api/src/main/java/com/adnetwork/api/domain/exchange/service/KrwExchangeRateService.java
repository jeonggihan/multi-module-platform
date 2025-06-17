package com.adnetwork.api.domain.exchange.service;

import com.adnetwork.core.variable.CurrencyCode;
import java.math.BigDecimal;

public interface KrwExchangeRateService extends ExchangeRateService {

  BigDecimal getKrwTo(CurrencyCode targetCurrency);

  BigDecimal getFromToKrw(CurrencyCode baseCurrency);

}
