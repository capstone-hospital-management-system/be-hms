package com.capstone.alta.hms.api.v1.patients.utils;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    public String code;

    private Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
