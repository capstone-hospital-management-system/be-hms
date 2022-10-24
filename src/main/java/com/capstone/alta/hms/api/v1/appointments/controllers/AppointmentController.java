package com.capstone.alta.hms.api.v1.appointments.controllers;

import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.services.IAppointmentService;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AppointmentController {
    @Autowired
    IAppointmentService appointmentService;

    @PostMapping("/accounts/{accountId}/appointments")
    public ResponseEntity<BaseResponseDTO<AppointmentResponseDTO>> createNewAppointment(
        @PathVariable Integer accountId,
        @RequestBody AppointmentRequestDTO appointmentRequestDTO) {

        BaseResponseDTO<AppointmentResponseDTO> response =
            appointmentService.createNewAppointment(accountId, appointmentRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
