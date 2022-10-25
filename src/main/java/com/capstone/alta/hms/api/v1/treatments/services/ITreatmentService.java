package com.capstone.alta.hms.api.v1.treatments.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.treatments.dtos.TreatmentRequestDTO;
import com.capstone.alta.hms.api.v1.treatments.dtos.TreatmentResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITreatmentService {
    BaseResponseDTO<TreatmentResponseDTO> createNewTreatment(TreatmentRequestDTO treatmentRequestDTO);
    PageBaseResponseDTO<List<TreatmentResponseDTO>> getAllTreatments(Pageable pageable);
    BaseResponseDTO<TreatmentResponseDTO> getTreatmentDetails(Integer id);
    BaseResponseDTO<TreatmentResponseDTO> updateTreatment(Integer id, TreatmentRequestDTO treatmentRequestDTO);
    BaseResponseDTO<TreatmentResponseDTO> deleteTreatment(Integer id);
}
