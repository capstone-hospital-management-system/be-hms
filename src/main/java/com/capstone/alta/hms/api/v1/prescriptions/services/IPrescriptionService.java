package com.capstone.alta.hms.api.v1.prescriptions.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionRequestDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPrescriptionService {
  BaseResponseDTO<PrescriptionResponseDTO> createNewPrescription(PrescriptionRequestDTO prescriptionRequestDTO);
  PageBaseResponseDTO<List<PrescriptionResponseDTO>> getAllPrescriptions(Pageable pageable);
  BaseResponseDTO<PrescriptionResponseDTO> getPrescriptionDetails(Integer id);
  BaseResponseDTO<PrescriptionResponseDTO> updatePrescription(Integer id, PrescriptionRequestDTO prescriptionRequestDTO);
  BaseResponseDTO<PrescriptionResponseDTO> deletePrescription(Integer id);
}
