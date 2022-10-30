package com.capstone.alta.hms.api.v1.patients.controllers;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        BaseResponseDTO<PatientResponseDTO> response = patientService.deletePatient(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/patients/download-excel")
    public ResponseEntity<Resource> downloadExcel() {
        String fileName = "patients.xlsx";
        String headerValue = "attachment; filename=" + fileName;
        InputStreamResource excelResource = new InputStreamResource(patientService.downloadExcel());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(excelResource);
    }
}
