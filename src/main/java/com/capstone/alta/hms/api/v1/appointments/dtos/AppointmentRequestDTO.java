package com.capstone.alta.hms.api.v1.appointments.dtos;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import com.capstone.alta.hms.api.v1.diagnoses.entities.Diagnose;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class AppointmentRequestDTO implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("appointment_date")
    @NotNull
    private LocalDateTime appointmentDate;

    @JsonProperty("created_at")
    @JsonIgnore
    private Date createdAt;

    @JsonProperty("created_at")
    @JsonIgnore
    private Date updatedAt;

    @NotNull
    @JsonProperty("patient_id")
    private Integer patientId;

    @NotNull
    @JsonProperty("doctor_id")
    private Integer doctorId;

    @NotNull
    @JsonProperty("clinic_id")
    private Integer clinicId;

    @NotNull
    @JsonProperty("created_by")
    private Integer createdBy;

    @NotNull
    @JsonProperty("updated_by")
    private Integer updatedBy;

    /**
     @NotNull
     private Patient patient;

     @NotNull
     private Account doctor;

     @NotNull
     private Clinic clinic;

     @NotNull
     @JsonProperty("created_by")
     private Account createdBy;

     @NotNull
     @JsonProperty("updated_by")
     private Account updatedBy;
     */
}
