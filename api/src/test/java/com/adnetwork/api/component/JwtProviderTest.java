package com.adnetwork.api.component;

import static org.assertj.core.api.Assertions.assertThat;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

  private static final JwtProvider JWT_PROVIDER = new JwtProvider();
  private static final String LOGIN_ID = "admin001";

  @DisplayName("유효한 JWT로부터 Claims 추출 확인")
  @Test
  void parseClaims_whenToken_shouldReturnClaims() {

    System.out.println("111");
    Claims claims = JWT_PROVIDER.parseClaims(
        "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbklkIjoiYWRtaW4wMDEiLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sIm1lbWJlcklkIjoxLCJzdWIiOiJhZG1pbjAwMSIsImlhdCI6MTc0OTcxMTI2NSwiZXhwIjoxNzQ5NzE0ODY1fQ.qUcLVxDvCLQMGNt1rg0kBi4n87uaEh_1DrPC1w_UzJw");
    assertThat(claims.getSubject()).isEqualTo(LOGIN_ID);
  }

  @DisplayName("refresh token 생성")
  @Test
  void createRefreshToken_whenLoginIdInput_shouldReturnJwtToken() {
    String refreshToken = JWT_PROVIDER.createRefreshToken(LOGIN_ID);
    assertThat(refreshToken).isNotBlank();
  }


}