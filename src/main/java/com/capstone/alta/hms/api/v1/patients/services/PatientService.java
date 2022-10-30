package com.capstone.alta.hms.api.v1.patients.services;

import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.capstone.alta.hms.api.v1.patients.repositories.PatientRepository;
import com.capstone.alta.hms.api.v1.websocket.dto.MessageDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public BaseResponseDTO<PatientResponseDTO> createNewPatient(PatientRequestDTO patientRequestDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Patient patient = patientRepository.save(modelMapper.map(patientRequestDTO, Patient.class));

        PatientResponseDTO patientResponseDTO = modelMapper.map(patient, PatientResponseDTO.class);

        MessageDTO message = new MessageDTO();
        message.setType("message");
        message.setTitle("NEW PATIENT: " + patientResponseDTO.getFirstName() + " " + patientResponseDTO.getLastName());
        message.setDescription(patientResponseDTO.getGender() + " - " + patientResponseDTO.getAge() + " y.o");

        messagingTemplate.convertAndSend("/topic/messages", message);

        return new BaseResponseDTO<>(
            "201",
            HttpStatus.CREATED,
            "successfully creating data",
            patientResponseDTO
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
    public BaseResponseDTO<PatientResponseDTO> updatePatient(Integer id, PatientRequestDTO patientRequestDTO) {
        Patient updatePatient = new Patient();
        updatePatient.setId(id);
        updatePatient.setRegisterBy(patientRequestDTO.getRegisterBy());
        updatePatient.setUpdatedBy(patientRequestDTO.getUpdatedBy());
        updatePatient.setFirstName(patientRequestDTO.getFirstName());
        updatePatient.setLastName(patientRequestDTO.getLastName());
        updatePatient.setIdCard(patientRequestDTO.getIdCard());
        updatePatient.setAge(patientRequestDTO.getAge());
        updatePatient.setGender(patientRequestDTO.getGender());
        updatePatient.setAddress(patientRequestDTO.getAddress());
        updatePatient.setCity(patientRequestDTO.getCity());
        updatePatient.setBloodType(patientRequestDTO.getBloodType());
        updatePatient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
        updatePatient.setPostalCode(patientRequestDTO.getPostalCode());
        updatePatient.setUsername(patientRequestDTO.getUsername());
        updatePatient.setBod(patientRequestDTO.getBod());

        Patient updatedPatient = patientRepository.save(updatePatient);

        return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully updating data",
                modelMapper.map(updatedPatient, PatientResponseDTO.class)
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