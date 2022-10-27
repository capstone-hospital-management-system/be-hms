package com.capstone.alta.hms.api.v1.accounts.dtos;

import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AccountRequestDTO implements Serializable {
    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Role role;

    @NotNull
    @JsonProperty("id_card")
    private String idCard;

    private String others;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
