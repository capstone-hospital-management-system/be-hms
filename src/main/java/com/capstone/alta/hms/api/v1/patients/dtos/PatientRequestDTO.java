package com.capstone.alta.hms.api.v1.patients.dtos;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.patients.utils.BloodType;
import com.capstone.alta.hms.api.v1.patients.utils.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Gender gender;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @JsonProperty("blood_type")
    private BloodType bloodType;

    @JsonProperty("phone_number")
    @NotNull
    private String phoneNumber;

    @JsonProperty("postal_code")
    @NotNull
    private String postalCode;

    private String username;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bod;

    @JsonProperty("register_by")
    private Account registerBy;

    @JsonProperty("updated_by")
    private Account updatedBy;

    @JsonIgnore
    @JsonProperty("created_at")
    private Date createdAt;

    @JsonIgnore
    @JsonProperty("updated_at")
    private Date updatedAt;
}
