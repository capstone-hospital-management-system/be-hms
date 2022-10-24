package com.capstone.alta.hms.api.v1.patients.utils;

public enum BloodType {
    O("O"),
    A("A"),
    B("B"),
    AB("AB");

    public String code;

    private BloodType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
