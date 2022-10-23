package com.capstone.alta.hms.api.v1.clinics.controllers;

import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicRequestDTO;
import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicResponseDTO;
import com.capstone.alta.hms.api.v1.clinics.services.IClinicService;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClinicController {

    @Autowired
    IClinicService clinicService;

    @PostMapping("/clinics")
    public ResponseEntity<BaseResponseDTO<ClinicResponseDTO>> createNewClinic(
            @RequestBody ClinicRequestDTO clinicRequestDTO) {
        BaseResponseDTO<ClinicResponseDTO> response =
            clinicService.createNewClinic(clinicRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/clinics")
    public ResponseEntity<PageBaseResponseDTO<List<ClinicResponseDTO>>> getAllClinics(
            Pageable pageable) {
        PageBaseResponseDTO<List<ClinicResponseDTO>> response =
            clinicService.getAllClinics(pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/clinics/{id}")
    public ResponseEntity<BaseResponseDTO<ClinicResponseDTO>> getClinicDetails(
            @PathVariable Integer id) {
        BaseResponseDTO<ClinicResponseDTO> response =
            clinicService.getClinicDetails(id);
//        if (response.getData() == null) {
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/clinics/{id}")
    public ResponseEntity<BaseResponseDTO<ClinicResponseDTO>> updateClinic(
            @PathVariable Integer id,
            @RequestBody ClinicRequestDTO clinicRequestDTO) {
        BaseResponseDTO<ClinicResponseDTO> response =
                clinicService.updateClinic(id, clinicRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/clinics/{id}")
    public ResponseEntity<BaseResponseDTO<ClinicResponseDTO>> deleteClinic(@PathVariable Integer id) {
        BaseResponseDTO<ClinicResponseDTO> response = clinicService.deleteClinic(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
