package com.adnetwork.api.domain.auth.service;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenService {

  private final RedisTemplate<String, String> redisTemplate;

  private static final String KEY_PREFIX = "IW:";

  private static final long REFRESH_TOKEN_VALIDITY_MS = 1000L * 60 * 60 * 24 * 7; // 7일

  public RefreshTokenService(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void save(String loginId, String refreshToken) {
    redisTemplate.opsForValue().set(
        KEY_PREFIX + loginId,
        refreshToken,
        REFRESH_TOKEN_VALIDITY_MS,
        TimeUnit.MILLISECONDS
    );
  }

  public String get(String loginId) {
    return redisTemplate.opsForValue().get(KEY_PREFIX + loginId);
  }

  public void delete(String loginId) {
    redisTemplate.delete(KEY_PREFIX + loginId);
  }
}
