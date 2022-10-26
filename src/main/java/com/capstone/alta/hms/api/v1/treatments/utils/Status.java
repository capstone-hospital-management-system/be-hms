package com.capstone.alta.hms.api.v1.treatments.utils;

public enum Status {
    IN_PROGRESS("IN_PROGRESS"),
    WAITING("WAITING"),
    DONE("DONE"),
    CANCEL("CANCEL");

    public String code;

    private Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
