package com.capstone.alta.hms.api.v1.appointments.dtos;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class AppointmentResponseDTO implements Serializable {
    @JsonProperty("appointment_date")
    private LocalDateTime appointmentDate;

    @JsonProperty("created_at")
    @JsonIgnore
    private Date createdAt;

    @JsonProperty("created_at")
    @JsonIgnore
    private Date updatedAt;

    @NotNull
    private Patient patient;

    @NotNull
    private Account doctor;

    @NotNull
    private Clinic clinic;
}
