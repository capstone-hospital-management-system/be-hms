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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
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

    @Override
    public ByteArrayInputStream downloadExcel() {
        List<Patient> patients = patientRepository.findAll();
        try (
                Workbook wb = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet1 = wb.createSheet("Sheet1");
            List<String> headers = new ArrayList<>();
            headers.add("Patient ID");
            headers.add("Username");
            headers.add("Firstname");
            headers.add("Lastname");
            headers.add("ID Card");
            headers.add("Age");
            headers.add("Gender");
            headers.add("Blood Type");
            headers.add("Phone Number");
            headers.add("Address");
            headers.add("City");
            headers.add("Postal Code");

            CellStyle cellStyle = wb.createCellStyle();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));

            Row headerRow = sheet1.createRow(0);

            for (int i = 0; i < headers.size(); i++) {
                Cell col = headerRow.createCell(i);
                col.setCellValue(headers.get(i));
            }

            int rowId = 1;
            for (Patient patient : patients) {
                Row row = sheet1.createRow(rowId);
                row.createCell(0, CellType.NUMERIC).setCellValue(patient.getId());
                row.createCell(1, CellType.STRING).setCellValue(patient.getUsername());
                row.createCell(2, CellType.STRING).setCellValue(patient.getFirstName());
                row.createCell(3, CellType.STRING).setCellValue(patient.getLastName());
                row.createCell(4, CellType.STRING).setCellValue(patient.getIdCard());
                row.createCell(5, CellType.STRING).setCellValue(patient.getAge());
                row.createCell(6, CellType.STRING).setCellValue(patient.getGender().toString());
                row.createCell(7, CellType.STRING).setCellValue(patient.getBloodType().toString());
                row.createCell(8, CellType.STRING).setCellValue(patient.getPhoneNumber());
                row.createCell(9, CellType.STRING).setCellValue(patient.getAddress());
                row.createCell(10, CellType.STRING).setCellValue(patient.getCity());
                row.createCell(11, CellType.STRING).setCellValue(patient.getPostalCode());
                rowId++;
            }

            wb.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOError | IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}