package com.capstone.alta.hms.api.v1.securities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationRequestDTO implements Serializable {
    @JsonProperty("username_or_email")
    private String usernameOrEmail;

    private String password;
}
