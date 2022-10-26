package com.capstone.alta.hms.api.v1.treatments.controllers;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.treatments.dtos.TreatmentRequestDTO;
import com.capstone.alta.hms.api.v1.treatments.dtos.TreatmentResponseDTO;
import com.capstone.alta.hms.api.v1.treatments.services.ITreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TreatmentController {

    @Autowired
    ITreatmentService treatmentService;

    @PostMapping("/treatments")
    public ResponseEntity<BaseResponseDTO<TreatmentResponseDTO>> createNewTreatments(@RequestBody TreatmentRequestDTO treatmentRequestDTO) {
        BaseResponseDTO<TreatmentResponseDTO> response = treatmentService.createNewTreatment(treatmentRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/treatments")
    public ResponseEntity<PageBaseResponseDTO<List<TreatmentResponseDTO>>> getAllTreatments(Pageable pageable) {
        PageBaseResponseDTO<List<TreatmentResponseDTO>> response = treatmentService.getAllTreatments(pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/treatments/{id}")
    public ResponseEntity<BaseResponseDTO<TreatmentResponseDTO>> getTreatmentDetails(@PathVariable Integer id) {
        BaseResponseDTO<TreatmentResponseDTO> response = treatmentService.getTreatmentDetails(id);

        if(response.getData() == null) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/treatments/{id}")
    public ResponseEntity<BaseResponseDTO<TreatmentResponseDTO>> updateTreatment(@PathVariable Integer id, @RequestBody TreatmentRequestDTO treatmentRequestDTO) {
        BaseResponseDTO<TreatmentResponseDTO> response = treatmentService.updateTreatment(id, treatmentRequestDTO);

        if(response.getData() == null) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/treatments/{id}")
    public ResponseEntity<BaseResponseDTO<TreatmentResponseDTO>> deleteTreatment(@PathVariable Integer id) {
        BaseResponseDTO<TreatmentResponseDTO> response = treatmentService.deleteTreatment(id);

        if(response.getData() == null) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
