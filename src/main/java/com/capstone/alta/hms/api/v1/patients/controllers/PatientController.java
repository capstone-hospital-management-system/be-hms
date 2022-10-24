package com.capstone.alta.hms.api.v1.patients.controllers;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    IPatientService patientService;

    @PostMapping("/accounts/{accountId}/patients")
    public ResponseEntity<BaseResponseDTO<PatientResponseDTO>> createNewPatient(
            @PathVariable Integer accountId, @RequestBody PatientRequestDTO patientRequestDTO) {
        BaseResponseDTO<PatientResponseDTO> response =
                patientService.createNewPatient(accountId, patientRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
