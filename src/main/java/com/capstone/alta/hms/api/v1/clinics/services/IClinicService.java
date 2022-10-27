package com.capstone.alta.hms.api.v1.clinics.services;

import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicRequestDTO;
import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClinicService {
    BaseResponseDTO<ClinicResponseDTO> createNewClinic(ClinicRequestDTO clinicRequestDTO);
    PageBaseResponseDTO<List<ClinicResponseDTO>> getAllClinics(Pageable pageable);
    BaseResponseDTO<ClinicResponseDTO> getClinicDetails(Integer id);
    BaseResponseDTO<ClinicResponseDTO> updateClinic(Integer id, ClinicRequestDTO clinicRequestDTO);
    BaseResponseDTO<ClinicResponseDTO> deleteClinic(Integer id);
}
