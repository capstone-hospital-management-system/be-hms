package com.capstone.alta.hms.api.v1.accounts.utils;

public enum Role {
    ADMINISTRATOR("A"),
    DOCTOR("D"),
    NURSE("N"),
    RECEPTIONIST("R"),
    OTHER("O");

    public String code;

    private Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
