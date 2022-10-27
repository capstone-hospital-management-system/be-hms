package com.capstone.alta.hms.api.v1.treatments.dtos;

import com.capstone.alta.hms.api.v1.treatments.utils.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TreatmentResponseDTO implements Serializable {
    private int id;

    @JsonProperty("diagnose_id")
    private int diagnoseId;

    private String report;

    private Status status;
}
