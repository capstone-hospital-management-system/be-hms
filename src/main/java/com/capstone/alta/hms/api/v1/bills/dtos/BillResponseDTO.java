package com.capstone.alta.hms.api.v1.bills.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class BillResponseDTO implements Serializable {
  private int id;

  @JsonProperty("prescription_id")
  private int prescriptionId;

  @JsonProperty("total_price")
  private BigDecimal totalPrice;
}
