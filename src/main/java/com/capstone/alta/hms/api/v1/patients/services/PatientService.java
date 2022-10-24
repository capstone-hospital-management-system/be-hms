package com.capstone.alta.hms.api.v1.patients.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.capstone.alta.hms.api.v1.patients.repositories.PatientRepository;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageBaseResponseDTO<List<PatientResponseDTO>> getAllPatients(Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(pageable);

        if (!patients.isEmpty()) {
            List<PatientResponseDTO> patientResponseDTOS = patients.stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDTO.class))
                .collect(Collectors.toList());

            return new PageBaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully retrieving data",
                patientResponseDTOS,
                new MetaResponseDTO(
                    patients.getNumber() + 1,
                    patients.getSize(),
                    patients.getTotalPages(),
                    patients.getTotalElements()
                )
            );
        }

        return new PageBaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "data is empty",
            Collections.emptyList(),
            new MetaResponseDTO(
                patients.getNumber() + 1,
                patients.getSize(),
                patients.getTotalPages(),
                patients.getTotalElements()
            )
        );
    }

    @Override
    public BaseResponseDTO<PatientResponseDTO> getPatientDetails(Integer id) {
        Patient patient = patientRepository.findById(id).get();
        return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "success",
            modelMapper.map(patient, PatientResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<PatientResponseDTO> updatePatient(
        Integer accountId,
        Integer id,
        PatientRequestDTO patientRequestDTO) {

        Optional<Patient> patient = accountRepository.findById(accountId)
            .map(account -> {
                Account registrarAccount = patientRepository.findById(id).get().getRegisterBy();
                patientRequestDTO.setUpdatedBy(account);
                Patient updatedPatient = modelMapper.map(patientRequestDTO, Patient.class);
                updatedPatient.setId(id);
                updatedPatient.setRegisterBy(registrarAccount);
                return patientRepository.save(updatedPatient);
            });

        return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully updating data",
                modelMapper.map(patient.get(), PatientResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<PatientResponseDTO> deletePatient(Integer id) {
        patientRepository.deleteById(id);

        return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully deleting data",
                null
        );
    }
}