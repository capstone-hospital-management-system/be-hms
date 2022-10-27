package com.capstone.alta.hms.api.v1.treatments.dtos;

import com.capstone.alta.hms.api.v1.treatments.utils.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import java.io.Serializable;

@Getter
@Setter
public class TreatmentRequestDTO implements Serializable {
    @NotNull
    @JsonProperty("diagnose_id")
    private int diagnoseId;

    @NotNull
    private String report;

    @NotNull
    private Status status;
}
