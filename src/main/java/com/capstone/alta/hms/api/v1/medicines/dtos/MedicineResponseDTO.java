package com.capstone.alta.hms.api.v1.medicines.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class MedicineResponseDTO implements Serializable {
  private int id;
  private String name;
  private String description;
  private BigDecimal price;
  private short stock;

  @Override
  public String toString() {
    return "MedicineResponseDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", stock=" + stock +
            '}';
  }
}
