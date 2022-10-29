package com.capstone.alta.hms.api.v1.prescriptions.dtos;

import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseResponseDTO;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.utils.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PrescriptionResponseDTO implements Serializable {
  private int id;
  private DiagnoseResponseDTO diagnose;
  private String description;
  private Status status;
  private String others;
  private List<MedicineResponseDTO> medicines;
}
