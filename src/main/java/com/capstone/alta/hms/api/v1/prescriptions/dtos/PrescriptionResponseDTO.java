package com.capstone.alta.hms.api.v1.prescriptions.dtos;

import com.capstone.alta.hms.api.v1.prescriptions.utils.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PrescriptionResponseDTO implements Serializable {
  private int id;
  @JsonProperty("diagnose_id")
  private int diagnoseId;
  private String description;
  private Status status;
  private String others;
}
