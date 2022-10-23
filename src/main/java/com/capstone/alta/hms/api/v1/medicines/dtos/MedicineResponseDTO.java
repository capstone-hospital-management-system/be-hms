package com.capstone.alta.hms.api.v1.medicines.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MedicineResponseDTO {
  private int id;
  private String name;
  private String description;
  private BigDecimal price;
  private short stock;
}
