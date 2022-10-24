package com.capstone.alta.hms.api.v1.patients.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;

public interface IPatientService {
    BaseResponseDTO<PatientResponseDTO> createNewPatient(Integer accountId, PatientRequestDTO patientRequestDTO);
}
