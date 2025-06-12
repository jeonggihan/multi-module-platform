package com.adnetwork.api.common;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CommonResponse<T> {

  private final T data;

  public static <T> CommonResponse<T> success() {
    return success(null);
  }

  public static <T> CommonResponse<T> success(T data) {
    return CommonResponse.<T>builder().data(data).build();
  }
}
