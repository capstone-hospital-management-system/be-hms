package com.capstone.alta.hms.api.v1.prescriptions.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionRequestDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.entities.Prescription;
import com.capstone.alta.hms.api.v1.prescriptions.repositories.PrescriptionRepository;
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
public class PrescriptionService implements IPrescriptionService {
  @Autowired
  PrescriptionRepository prescriptionRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> createNewPrescription(PrescriptionRequestDTO prescriptionRequestDTO) {
    Prescription prescription = prescriptionRepository.save(modelMapper.map(prescriptionRequestDTO, Prescription.class));
    PrescriptionResponseDTO prescriptionResponseDTO = modelMapper.map(prescription, PrescriptionResponseDTO.class);
    return new BaseResponseDTO<PrescriptionResponseDTO>(
            "201",
            HttpStatus.CREATED,
            "successfully creating data",
            prescriptionResponseDTO
    );
  }

  @Override
  public PageBaseResponseDTO<List<PrescriptionResponseDTO>> getAllPrescriptions(Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findAll(pageable);

    String message = "";
    List<PrescriptionResponseDTO> prescriptionResponseDTOS;

    if (prescriptions.isEmpty()) {
      message = "data is empty";
      prescriptionResponseDTOS = Collections.emptyList();
    } else {
      message = "successfully retrieving data";
      prescriptionResponseDTOS = prescriptions.stream()
              .map(prescription -> modelMapper.map(prescription, PrescriptionResponseDTO.class))
              .collect(Collectors.toList());
    }

    return new PageBaseResponseDTO<List<PrescriptionResponseDTO>>(
            "200",
            HttpStatus.OK,
            message,
            prescriptionResponseDTOS,
            new MetaResponseDTO(
                    prescriptions.getNumber() + 1,
                    prescriptions.getSize(),
                    prescriptions.getTotalPages(),
                    prescriptions.getTotalElements()
            )
    );
  }

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> getPrescriptionDetails(Integer id) {
    Prescription prescription = prescriptionRepository.findById(id).get();
    return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "success",
            modelMapper.map(prescription, PrescriptionResponseDTO.class)
    );
  }

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> updatePrescription(Integer id, PrescriptionRequestDTO prescriptionRequestDTO) {
    Prescription prescriptionUpdate = modelMapper.map(prescriptionRequestDTO, Prescription.class);
    prescriptionUpdate.setId(id);

    Prescription prescription = prescriptionRepository.save(prescriptionUpdate);

    return new BaseResponseDTO<PrescriptionResponseDTO>(
            "200",
            HttpStatus.OK,
            "successfully updating data",
            modelMapper.map(prescription, PrescriptionResponseDTO.class)
    );
  }

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> deletePrescription(Integer id) {
    prescriptionRepository.deleteById(id);
    return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "successfully deleting data",
            null
    );
  }
}
