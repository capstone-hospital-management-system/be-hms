package com.capstone.alta.hms.api.v1.clinics.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClinicResponseDTO implements Serializable {
    private int id;
    private String name;
    private String description;
}
