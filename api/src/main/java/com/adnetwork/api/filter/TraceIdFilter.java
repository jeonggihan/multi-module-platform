package com.adnetwork.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

public class TraceIdFilter extends OncePerRequestFilter {

  private static final String TRACE_ID = "traceId";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String traceId = UUID.randomUUID().toString();
    MDC.put(TRACE_ID, traceId);
    try {
      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(TRACE_ID);
    }
  }
}
