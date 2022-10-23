package com.capstone.alta.hms.api.v1.medicines.controllers;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineRequestDTO;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineResponseDTO;
import com.capstone.alta.hms.api.v1.medicines.services.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MedicineController {
  @Autowired
  IMedicineService medicineService;

  @PostMapping("/medicines")
  public ResponseEntity<BaseResponseDTO<MedicineResponseDTO>> createNewMedicine (
          @RequestBody MedicineRequestDTO medicineRequestDTO) {
    BaseResponseDTO<MedicineResponseDTO> response =
            medicineService.createNewMedicine(medicineRequestDTO);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/medicines")
  public ResponseEntity<PageBaseResponseDTO<List<MedicineResponseDTO>>> getAllMedicines(
          Pageable pageable) {
    PageBaseResponseDTO<List<MedicineResponseDTO>> response =
            medicineService.getAllMedicines(pageable);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/medicines/{id}")
  public ResponseEntity<BaseResponseDTO<MedicineResponseDTO>> getMedicineDetails(
          @PathVariable Integer id) {
    BaseResponseDTO<MedicineResponseDTO> response =
            medicineService.getMedicineDetails(id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/medicines/{id}")
  public ResponseEntity<BaseResponseDTO<MedicineResponseDTO>> updateMedicine(
          @PathVariable Integer id,
          @RequestBody MedicineRequestDTO medicineRequestDTO) {
    BaseResponseDTO<MedicineResponseDTO> response =
            medicineService.updateMedicine(id, medicineRequestDTO);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/medicines/{id}")
  public ResponseEntity<BaseResponseDTO<MedicineResponseDTO>> deleteMedicine(@PathVariable Integer id) {
    BaseResponseDTO response = medicineService.deleteMedicine(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
