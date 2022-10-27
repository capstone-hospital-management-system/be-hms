package com.capstone.alta.hms.api.v1.bills.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class BillRequestDTO implements Serializable {
  @JsonProperty("prescription_id")
  private int prescriptionId;
  @JsonProperty("total_price")
  private BigDecimal totalPrice;
}
