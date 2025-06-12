package com.adnetwork.api.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EntityScan(basePackages = "com.adnetwork.common")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfiguration {

}
