package com.capstone.alta.hms.api.v1.prescriptions.utils;

public enum Status {
  WAITING_PAYMENT("WAITING_PAYMENT"),
  DONE("DONE"),
  CANCEL("CANCEL");

  private String code;

  private Status(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
