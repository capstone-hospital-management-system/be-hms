package com.capstone.alta.hms.api.v1.diagnoses.dtos;

import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DiagnoseResponseDTO implements Serializable {
    private int id;
    private String name;
    private String description;
    private String report;
    private AppointmentResponseDTO appointment;
}
