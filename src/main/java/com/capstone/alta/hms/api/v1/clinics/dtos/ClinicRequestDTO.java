package com.capstone.alta.hms.api.v1.clinics.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClinicRequestDTO implements Serializable {
    @NotNull
    private String name;

    @NotNull
    private String description;
}
