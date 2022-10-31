package com.capstone.alta.hms.api.v1.diagnoses.controllers;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseRequestDTO;
import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.services.IDiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class DiagnoseController {

    @Autowired
    IDiagnoseService diagnoseService;

    @PostMapping("/diagnoses")
    public ResponseEntity<BaseResponseDTO<DiagnoseResponseDTO>> createNewDiagnoses(@RequestBody DiagnoseRequestDTO diagnoseRequestDTO) {
        BaseResponseDTO<DiagnoseResponseDTO> response = diagnoseService.createDiagnose(diagnoseRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/diagnoses")
    public ResponseEntity<PageBaseResponseDTO<List<DiagnoseResponseDTO>>> getAllDiagnoses(Pageable pageable) {
        PageBaseResponseDTO<List<DiagnoseResponseDTO>> response = diagnoseService.getAllDiagnoses(pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/diagnoses/{id}")
    public ResponseEntity<BaseResponseDTO<DiagnoseResponseDTO>> getDiagnoseDetail(@PathVariable Integer id) {
        BaseResponseDTO<DiagnoseResponseDTO> response = diagnoseService.getDiagnoseDetail(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/diagnoses/{id}")
    public ResponseEntity<BaseResponseDTO<DiagnoseResponseDTO>> updateDiagnose(@PathVariable Integer id, @RequestBody DiagnoseRequestDTO diagnoseRequestDTO) {
        BaseResponseDTO<DiagnoseResponseDTO> response = diagnoseService.updateDiagnose(id, diagnoseRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/diagnoses/{id}")
    public ResponseEntity<BaseResponseDTO<DiagnoseResponseDTO>> deleteDiagnose(@PathVariable Integer id) {
        BaseResponseDTO<DiagnoseResponseDTO> response = diagnoseService.deleteDiagnose(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
