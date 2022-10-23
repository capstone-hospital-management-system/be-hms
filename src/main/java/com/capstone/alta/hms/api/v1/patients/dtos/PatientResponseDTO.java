package com.capstone.alta.hms.api.v1.patients.dtos;

import com.capstone.alta.hms.api.v1.patients.utils.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PatientResponseDTO implements Serializable {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("id_card")
    private String idCard;

    private short age;

    private Gender gender; //change this to enum latter

    private String address;

    private String city;

    @JsonProperty("blood_type")
    private String bloodType; //change this to enum latter

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("postal_code")
    private String postalCode;

    private String username;

    private Date bod;
}
