package com.adnetwork.api.domain.member.repository;

import com.adnetwork.api.domain.member.dto.LoginUserDto;
import java.util.List;

public interface ScreenPermissionRepository {

  List<LoginUserDto.ScreenPermissionDto> findPermissionsByRoleIds(List<Long> roleIds);
}
