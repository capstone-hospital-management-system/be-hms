package com.capstone.alta.hms.api.v1.diagnoses.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseRequestDTO;
import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.entities.Diagnose;
import com.capstone.alta.hms.api.v1.diagnoses.repositories.DiagnoseRepository;
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
public class DiagnoseService implements IDiagnoseService {

    @Autowired
    DiagnoseRepository diagnoseRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<DiagnoseResponseDTO> createDiagnose(DiagnoseRequestDTO diagnoseRequestDTO) {
        Diagnose diagnose = diagnoseRepository.save(modelMapper.map(diagnoseRequestDTO, Diagnose.class));
        DiagnoseResponseDTO diagnoseResponseDTO = modelMapper.map(diagnose, DiagnoseResponseDTO.class);

        return new BaseResponseDTO<DiagnoseResponseDTO>(
                "201",
                HttpStatus.CREATED,
                "suscessfully creating data",
                diagnoseResponseDTO
        );
    }

    @Override
    public PageBaseResponseDTO<List<DiagnoseResponseDTO>> getAllDiagnoses(Pageable pageable) {
        Page<Diagnose> diagnoses = diagnoseRepository.findAll(pageable);

        if(!diagnoses.isEmpty()) {
            List<DiagnoseResponseDTO> diagnoseResponseDTOS = diagnoses.stream()
                    .map(diagnose -> modelMapper.map(diagnose, DiagnoseResponseDTO.class))
                    .collect(Collectors.toList());

            return new PageBaseResponseDTO<List<DiagnoseResponseDTO>>(
                    "200",
                    HttpStatus.OK,
                    "successfully retrieving data",
                    diagnoseResponseDTOS,
                    new MetaResponseDTO(
                            diagnoses.getNumber() +1,
                            diagnoses.getSize(),
                            diagnoses.getTotalPages(),
                            diagnoses.getTotalElements()
                    )
            );
        }

        return new PageBaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "data is empty",
                Collections.emptyList(),
                new MetaResponseDTO(
                        diagnoses.getNumber() +1,
                        diagnoses.getSize(),
                        diagnoses.getTotalPages(),
                        diagnoses.getTotalElements()
                )
        );
    }

    @Override
    public BaseResponseDTO<DiagnoseResponseDTO> getDiagnoseDetail(Integer id) {
        Diagnose diagnose = diagnoseRepository.findById(id).orElse(null);

        if(diagnose != null) {
            DiagnoseResponseDTO diagnoseResponseDTO = modelMapper.map(diagnose, DiagnoseResponseDTO.class);
            return new BaseResponseDTO<>(
                    "200",
                    HttpStatus.OK,
                    "successfully retrieving data",
                    diagnoseResponseDTO
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

    @Override
    public BaseResponseDTO<DiagnoseResponseDTO> updateDiagnose(Integer id, DiagnoseRequestDTO diagnoseRequestDTO) {
        Diagnose diagnose = diagnoseRepository.findById(id).orElse(null);

        if(diagnose != null) {
            modelMapper.map(diagnoseRequestDTO, diagnose);
            diagnoseRepository.save(diagnose);

            DiagnoseResponseDTO diagnoseResponseDTO = modelMapper.map(diagnose, DiagnoseResponseDTO.class);

            return new BaseResponseDTO<>(
                    "200",
                    HttpStatus.OK,
                    "successfully updating data",
                    diagnoseResponseDTO
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

    @Override
    public BaseResponseDTO<DiagnoseResponseDTO> deleteDiagnose(Integer id) {
        Diagnose diagnose = diagnoseRepository.findById(id).orElse(null);

        if(diagnose != null) {
            diagnoseRepository.delete(diagnose);
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
