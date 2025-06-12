package com.adnetwork.api.domain.member.dto;

import com.adnetwork.core.variable.StatusCode;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserDto {

  private final Long memberId;
  private final String loginId;
  private final String password;
  private final String name;
  private final String phoneNumber;
  private final StatusCode used; // USED / NOT_USED

  private final List<String> roles; // ex: ["ROLE_ADMIN", "ROLE_USER"]
  private final List<ScreenPermissionDto> permissions;

  public record ScreenPermissionDto(String screenCode, StatusCode canRead, StatusCode canWrite) {

  }
}
