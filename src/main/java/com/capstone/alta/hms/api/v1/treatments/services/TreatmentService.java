package com.capstone.alta.hms.api.v1.treatments.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.entities.Diagnose;
import com.capstone.alta.hms.api.v1.diagnoses.repositories.DiagnoseRepository;
import com.capstone.alta.hms.api.v1.treatments.dtos.TreatmentRequestDTO;
import com.capstone.alta.hms.api.v1.treatments.dtos.TreatmentResponseDTO;
import com.capstone.alta.hms.api.v1.treatments.entities.Treatment;
import com.capstone.alta.hms.api.v1.treatments.repositories.TreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentService implements ITreatmentService {

    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    DiagnoseRepository diagnoseRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<TreatmentResponseDTO> createNewTreatment(TreatmentRequestDTO treatmentRequestDTO) {
        Diagnose diagnose = diagnoseRepository.findById(treatmentRequestDTO.getDiagnoseId()).get();
        Treatment newTreatment = new Treatment();
        newTreatment.setDiagnose(diagnose);
        newTreatment.setReport(treatmentRequestDTO.getReport());
        newTreatment.setStatus(treatmentRequestDTO.getStatus());
        Treatment treatment = treatmentRepository.save(newTreatment);
        TreatmentResponseDTO treatmentResponseDTO = modelMapper.map(treatment, TreatmentResponseDTO.class);

        return new BaseResponseDTO<TreatmentResponseDTO>(
                "201",
                HttpStatus.CREATED,
                "successfully creating data",
                treatmentResponseDTO
        );
    }

    @Override
    public PageBaseResponseDTO<List<TreatmentResponseDTO>> getAllTreatments(Pageable pageable) {
        Page<Treatment> treatments = treatmentRepository.findAll(pageable);

        if(!treatments.isEmpty()) {
            List<TreatmentResponseDTO> treatmentResponseDTOS = treatments.stream()
                .map(treatment -> modelMapper.map(treatment, TreatmentResponseDTO.class))
                .collect(Collectors.toList());

            return new PageBaseResponseDTO<List<TreatmentResponseDTO>>(
                    "200",
                    HttpStatus.OK,
                    "successfully retrieving data",
                    treatmentResponseDTOS,
                    new MetaResponseDTO(
                            treatments.getNumber() +1,
                            treatments.getSize(),
                            treatments.getTotalPages(),
                            treatments.getTotalElements()
                    )
            );
        }

        return new PageBaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "data is empty",
                Collections.emptyList(),
                new MetaResponseDTO(
                        treatments.getNumber() +1,
                        treatments.getSize(),
                        treatments.getTotalPages(),
                        treatments.getTotalElements()
                )
        );
    }

    @Override
    public BaseResponseDTO<TreatmentResponseDTO> getTreatmentDetails(Integer id) {
        Treatment treatment = treatmentRepository.findById(id).orElse(null);

        if(treatment != null) {
            TreatmentResponseDTO treatmentResponseDTO = modelMapper.map(treatment, TreatmentResponseDTO.class);

            return new BaseResponseDTO<TreatmentResponseDTO>(
                    "200",
                    HttpStatus.OK,
                    "successfully retrieving data",
                    treatmentResponseDTO
            );
        } else {
            return new BaseResponseDTO<TreatmentResponseDTO>(
                    "404",
                    HttpStatus.NOT_FOUND,
                    "data not found",
                    null
            );
        }
    }

    @Override
    public BaseResponseDTO<TreatmentResponseDTO> updateTreatment(Integer id, TreatmentRequestDTO treatmentRequestDTO) {
        Diagnose diagnose = diagnoseRepository.findById(treatmentRequestDTO.getDiagnoseId()).get();
        Treatment newTreatment = new Treatment();
        newTreatment.setId(id);
        newTreatment.setDiagnose(diagnose);
        newTreatment.setReport(treatmentRequestDTO.getReport());
        newTreatment.setStatus(treatmentRequestDTO.getStatus());
        Treatment treatment = treatmentRepository.save(newTreatment);
        TreatmentResponseDTO treatmentResponseDTO = modelMapper.map(treatment, TreatmentResponseDTO.class);

        return new BaseResponseDTO<TreatmentResponseDTO>(
                "200",
                HttpStatus.OK,
                "successfully updating data",
                treatmentResponseDTO
        );
    }

    @Override
    public BaseResponseDTO<TreatmentResponseDTO> deleteTreatment(Integer id) {
        Treatment treatment = treatmentRepository.findById(id).orElse(null);

        if(treatment != null) {
            treatmentRepository.delete(treatment);

            return new BaseResponseDTO<>(
                    "200",
                    HttpStatus.OK,
                    "successfully deleting data",
                    null
            );
        } else {
            return new BaseResponseDTO<>(
                    "404",
                    HttpStatus.NOT_FOUND,
                    "data not found",
                    null
            );
        }
    }
}
