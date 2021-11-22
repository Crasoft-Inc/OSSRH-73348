package com.crasoftinc.exceptionsjma.exceptions;

import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorModel {

  private final String message;
  private final String code;
  private final int status;
  private final long timestamp;

  public CustomErrorModel(String message, String code, int status) {
    this.message = message;
    this.code = code;
    this.status = status;
    this.timestamp = Instant.now().toEpochMilli();
  }
}
