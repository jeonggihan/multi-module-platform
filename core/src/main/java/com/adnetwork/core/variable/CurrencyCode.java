package com.adnetwork.core.variable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum CurrencyCode {

    USD("미국 달러"),
    KRW("대한민국 원"),
    EUR("유로"),
    JPY("일본 엔"),
    GBP("영국 파운드"),
    CNY("중국 위안"),
    AUD("호주 달러"),
    CAD("캐나다 달러"),
    SGD("싱가포르 달러");

    private final String description;

    CurrencyCode(String description) {
        this.description = description;
    }
}
