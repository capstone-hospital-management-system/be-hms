package com.capstone.alta.hms.api.v1.patients.controllers;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    IPatientService patientService;

    @PostMapping("/patients")
    public ResponseEntity<BaseResponseDTO<PatientResponseDTO>> createNewPatient(
            @RequestBody PatientRequestDTO patientRequestDTO) {
        BaseResponseDTO<PatientResponseDTO> response =
                patientService.createNewPatient(patientRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/patients")
    public ResponseEntity<PageBaseResponseDTO<List<PatientResponseDTO>>> getAllPatients(
        Pageable pageable) {
        PageBaseResponseDTO<List<PatientResponseDTO>> response =
            patientService.getAllPatients(pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<BaseResponseDTO<PatientResponseDTO>> getPatientDetails(
        @PathVariable Integer id) {
        BaseResponseDTO<PatientResponseDTO> response = patientService.getPatientDetails(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<BaseResponseDTO<PatientResponseDTO>> updatePatient(
        @PathVariable Integer id,
        @RequestBody PatientRequestDTO patientRequestDTO) {
        BaseResponseDTO<PatientResponseDTO> response =
            patientService.updatePatient(id, patientRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<BaseResponseDTO<PatientResponseDTO>> deleteAccount(@PathVariable Integer id) {
        BaseResponseDTO response = patientService.deletePatient(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
