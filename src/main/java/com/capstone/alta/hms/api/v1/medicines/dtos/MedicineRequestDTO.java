package com.capstone.alta.hms.api.v1.medicines.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class MedicineRequestDTO implements Serializable {
  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private BigDecimal price;

  @NotNull
  private short stock;
}
