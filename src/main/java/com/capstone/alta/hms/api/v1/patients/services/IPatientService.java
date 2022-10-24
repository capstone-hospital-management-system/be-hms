package com.capstone.alta.hms.api.v1.patients.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPatientService {
    BaseResponseDTO<PatientResponseDTO> createNewPatient(Integer accountId, PatientRequestDTO patientRequestDTO);
    PageBaseResponseDTO<List<PatientResponseDTO>> getAllPatients(Pageable pageable);
}
