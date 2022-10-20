package com.capstone.alta.hms.api.v1.administrators.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AdministratorResponseDTO implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;

    @JsonProperty("id_card_number")
    private String idCardNumber;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
