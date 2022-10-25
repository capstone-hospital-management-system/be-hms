package com.capstone.alta.hms.api.v1.diagnoses.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseRequestDTO;
import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDiagnoseService {
    BaseResponseDTO<DiagnoseResponseDTO> createDiagnose(DiagnoseRequestDTO diagnoseRequestDTO);
    PageBaseResponseDTO<List<DiagnoseResponseDTO>> getAllDiagnoses(Pageable pageable);
    BaseResponseDTO<DiagnoseResponseDTO> getDiagnoseDetail(Integer id);
    BaseResponseDTO<DiagnoseResponseDTO> updateDiagnose(Integer id, DiagnoseRequestDTO diagnoseRequestDTO);
    BaseResponseDTO<DiagnoseResponseDTO> deleteDiagnose(Integer id);
}
