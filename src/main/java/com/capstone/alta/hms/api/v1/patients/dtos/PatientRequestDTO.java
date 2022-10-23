package com.capstone.alta.hms.api.v1.patients.dtos;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.patients.utils.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PatientRequestDTO implements Serializable {
    @JsonProperty("first_name")
    @NotNull
    private String firstName;

    @JsonProperty("last_name")
    @NotNull
    private String lastName;

    @JsonProperty("id_card")
    @NotNull
    private String idCard;

    @NotNull
    private short age;

    @NotNull
    private Gender gender; //change this to enum latter

    @NotNull
    private String address;

    @NotNull
    private String city;

    @JsonProperty("blood_type")
    private String bloodType; //change this to enum latter

    @JsonProperty("phone_number")
    @NotNull
    private String phoneNumber;

    @JsonProperty("postal_code")
    @NotNull
    private String postalCode;

    private String username;

    @NotNull
    private Date bod;
}
