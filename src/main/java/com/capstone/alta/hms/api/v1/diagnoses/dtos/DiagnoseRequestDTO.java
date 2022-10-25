package com.capstone.alta.hms.api.v1.diagnoses.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DiagnoseRequestDTO implements Serializable {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String report;

    @NotNull
    @JsonProperty("appointment_id")
    private int appointmentId;
}
