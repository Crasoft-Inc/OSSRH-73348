package com.crasoftinc.exceptionsjma.ActionHistoryModel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TagModel {
  DANGER("DANGER"),
  WARNING("WARNING"),
  INFO("INFO");

  String status;

  TagModel(String status) {
    this.status = status;
  }

  @JsonValue
  public String getStatus() {
    return status;
  }
}
