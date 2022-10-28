package com.capstone.alta.hms.api.v1.appointments.controllers;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.services.IAppointmentService;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AppointmentController {
    @Autowired
    IAppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<BaseResponseDTO<AppointmentResponseDTO>> createNewAppointment(
        @RequestBody AppointmentRequestDTO appointmentRequestDTO) {

        BaseResponseDTO<AppointmentResponseDTO> response =
            appointmentService.createNewAppointment(appointmentRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/appointments")
    public ResponseEntity<PageBaseResponseDTO<List<AppointmentResponseDTO>>> getAllAppointments(
            Pageable pageable) {
        PageBaseResponseDTO<List<AppointmentResponseDTO>> response =
                appointmentService.getAllAppointments(pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<BaseResponseDTO<AppointmentResponseDTO>> getAppointmentDetails(
            @PathVariable Integer id) {
        BaseResponseDTO<AppointmentResponseDTO> response =
                appointmentService.getAppointmentDetails(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<BaseResponseDTO<AppointmentResponseDTO>> updateAppointment(
            @PathVariable Integer id,
            @RequestBody AppointmentRequestDTO appointmentRequestDTO) {

        BaseResponseDTO<AppointmentResponseDTO> response =
                appointmentService.updateAppointment(id, appointmentRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
