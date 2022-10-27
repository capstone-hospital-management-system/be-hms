package com.capstone.alta.hms.api.v1.accounts.dtos;

import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Converter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AccountResponseDTO implements Serializable {
    private int id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String username;

    private String email;

    private Role role;

    @JsonProperty("id_card")
    private String idCard;

    private String others;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
