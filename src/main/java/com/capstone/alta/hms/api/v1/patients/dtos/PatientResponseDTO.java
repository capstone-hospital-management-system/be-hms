package com.capstone.alta.hms.api.v1.patients.dtos;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.patients.utils.BloodType;
import com.capstone.alta.hms.api.v1.patients.utils.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PatientResponseDTO implements Serializable {
    private Integer id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("id_card")
    private String idCard;

    private short age;

    private Gender gender;

    private String address;

    private String city;

    @JsonProperty("blood_type")
    private BloodType bloodType;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("postal_code")
    private String postalCode;

    private String username;

    private Date bod;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("register_by")
    private AccountResponseDTO registerBy;

    @JsonProperty("updated_by")
    private AccountResponseDTO updatedBy;
}
