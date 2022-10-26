package com.capstone.alta.hms.api.v1.bills.dtos;

import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class BillResponseDTO implements Serializable {
  private int id;
  @JsonProperty("total_price")
  private BigDecimal totalPrice;
  private PrescriptionResponseDTO prescription;
}
