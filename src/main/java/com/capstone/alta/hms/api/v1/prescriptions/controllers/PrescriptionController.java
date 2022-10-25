package com.capstone.alta.hms.api.v1.prescriptions.controllers;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionRequestDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.services.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PrescriptionController {
  @Autowired
  IPrescriptionService prescriptionService;

  @PostMapping("/prescriptions")
  public ResponseEntity<BaseResponseDTO<PrescriptionResponseDTO>> createNewPrescription(
          @RequestBody PrescriptionRequestDTO prescriptionRequestDTO) {
    BaseResponseDTO<PrescriptionResponseDTO> response = prescriptionService.createNewPrescription(prescriptionRequestDTO);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/prescriptions")
  public ResponseEntity<PageBaseResponseDTO<List<PrescriptionResponseDTO>>> getAllPrescriptions(
          Pageable pageable) {
    PageBaseResponseDTO<List<PrescriptionResponseDTO>> response =
            prescriptionService.getAllPrescriptions(pageable);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/prescriptions/{id}")
  public ResponseEntity<BaseResponseDTO<PrescriptionResponseDTO>> getPrescriptionDetails(
          @PathVariable Integer id) {
    BaseResponseDTO<PrescriptionResponseDTO> response =
            prescriptionService.getPrescriptionDetails(id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/prescriptions/{id}")
  public ResponseEntity<BaseResponseDTO<PrescriptionResponseDTO>> updatePrescription(
          @PathVariable Integer id,
          @RequestBody PrescriptionRequestDTO prescriptionRequestDTO) {
    BaseResponseDTO<PrescriptionResponseDTO> response =
            prescriptionService.updatePrescription(id, prescriptionRequestDTO);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/prescriptions/{id}")
  public ResponseEntity<BaseResponseDTO<PrescriptionResponseDTO>> deletePrescription(@PathVariable Integer id) {
    BaseResponseDTO<PrescriptionResponseDTO> response = prescriptionService.deletePrescription(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
