package com.capstone.alta.hms.api.v1.clinics.services;

import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicRequestDTO;
import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicResponseDTO;
import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import com.capstone.alta.hms.api.v1.clinics.repositories.ClinicRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
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
public class ClinicService implements IClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<ClinicResponseDTO> createNewClinic(
        ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = clinicRepository.save(
            modelMapper.map(clinicRequestDTO, Clinic.class)
        );

        ClinicResponseDTO clinicResponseDTO = modelMapper.map(
            clinic, ClinicResponseDTO.class);

        return new BaseResponseDTO<ClinicResponseDTO>(
            "201",
            HttpStatus.CREATED,
            "successfully creating data",
            clinicResponseDTO
        );
    }


    @Override
    public PageBaseResponseDTO<List<ClinicResponseDTO>> getAllClinics(Pageable pageable) {
        Page<Clinic> clinics = clinicRepository.findAll(pageable);

        if (!clinics.isEmpty()) {
            List<ClinicResponseDTO> clinicResponseDTOS = clinics.stream()
                .map(clinic -> modelMapper.map(clinic, ClinicResponseDTO.class))
                .collect(Collectors.toList());

            return new PageBaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully retrieving data",
                clinicResponseDTOS,
                new MetaResponseDTO(
                    clinics.getNumber() + 1,
                    clinics.getSize(),
                    clinics.getTotalPages(),
                    clinics.getTotalElements()
                )
            );
        }

        return new PageBaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "data is empty",
            Collections.emptyList(),
            new MetaResponseDTO(
                clinics.getNumber() + 1,
                clinics.getSize(),
                clinics.getTotalPages(),
                clinics.getTotalElements()
            )
        );
    }

    @Override
    public BaseResponseDTO<ClinicResponseDTO> getClinicDetails(Integer id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);

        if (clinic != null) {
            ClinicResponseDTO clinicResponseDTO = modelMapper.map(
                clinic, ClinicResponseDTO.class);

            return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully retrieving data",
                clinicResponseDTO
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
    public BaseResponseDTO<ClinicResponseDTO> updateClinic(Integer id, ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);

        if(clinic != null) {
            modelMapper.map(clinicRequestDTO, clinic);
            clinicRepository.save(clinic);

            ClinicResponseDTO clinicResponseDTO = modelMapper.map(
                clinic, ClinicResponseDTO.class);

            return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully updating data",
                clinicResponseDTO
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
    public BaseResponseDTO<ClinicResponseDTO> deleteClinic(Integer id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);

        if(clinic != null) {
            clinicRepository.delete(clinic);
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
