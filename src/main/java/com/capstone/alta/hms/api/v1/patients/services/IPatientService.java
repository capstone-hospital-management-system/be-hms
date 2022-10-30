package com.capstone.alta.hms.api.v1.patients.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface IPatientService {
    BaseResponseDTO<PatientResponseDTO> createNewPatient(PatientRequestDTO patientRequestDTO);
    PageBaseResponseDTO<List<PatientResponseDTO>> getAllPatients(Pageable pageable);
    BaseResponseDTO<PatientResponseDTO> getPatientDetails(Integer id);
    BaseResponseDTO<PatientResponseDTO> updatePatient(Integer id, PatientRequestDTO patientRequestDTO);
    BaseResponseDTO<PatientResponseDTO> deletePatient(Integer id);
    ByteArrayInputStream downloadExcel();
}
