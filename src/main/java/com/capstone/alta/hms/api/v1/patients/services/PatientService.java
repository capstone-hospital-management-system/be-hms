package com.capstone.alta.hms.api.v1.patients.services;

import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.capstone.alta.hms.api.v1.patients.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<PatientResponseDTO> createNewPatient(
        Integer accountId, PatientRequestDTO patientRequestDTO) {
        Optional<Patient> patient = accountRepository.findById(accountId)
                .map(account -> {
                    patientRequestDTO.setRegisterBy(account);
                    patientRequestDTO.setUpdatedBy(account);
                    return patientRepository.save(modelMapper.map(patientRequestDTO, Patient.class));
                });

        return new BaseResponseDTO<>(
                "201",
                HttpStatus.CREATED,
                "successfully creating data",
                modelMapper.map(patient.get(), PatientResponseDTO.class)
        );
    }
}