package com.capstone.alta.hms.api.v1.administrators.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AdministratorRequestDTO implements Serializable {
    @NonNull
    private String name;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @NonNull
    @JsonProperty("id_card_number")
    private String idCardNumber;

    @NonNull
    @JsonProperty("phone_number")
    private String phoneNumber;
}
