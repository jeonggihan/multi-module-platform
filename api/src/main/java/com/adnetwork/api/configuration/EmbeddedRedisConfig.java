package com.adnetwork.api.configuration;

import jakarta.annotation.PreDestroy;
import java.io.File;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import redis.embedded.RedisServer;

@Slf4j
@Configuration
public class EmbeddedRedisConfig {

  private static final String OS = System.getProperty("os.name").toLowerCase();

  private final RedisServer redisServer;
  private static final int DEFAULT_PORT = 6379;

  public EmbeddedRedisConfig() {
    if (isWindow()) {
      redisServer = new RedisServer(DEFAULT_PORT);
    } else {
      redisServer = new RedisServer(Objects.requireNonNull(getRedisServerExecutable()), DEFAULT_PORT);
    }
    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    redisServer.stop();
  }

  private File getRedisServerExecutable() {
    try {
      return new ClassPathResource("redis/redis-server-6.0.10").getFile();
    } catch (Exception e) {
      throw new IllegalCallerException("redis server executable not found");
    }
  }

  private static boolean isWindow() {
    return OS.contains("win");
  }

}