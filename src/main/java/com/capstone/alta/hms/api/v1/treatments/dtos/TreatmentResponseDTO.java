package com.capstone.alta.hms.api.v1.treatments.dtos;

import com.capstone.alta.hms.api.v1.treatments.utils.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TreatmentResponseDTO implements Serializable {
    private int id;

    private int diagnose_id;

    private String report;

    private Status status;
}
