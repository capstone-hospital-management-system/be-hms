package com.capstone.alta.hms.api.v1.appointments.services;

import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;

public interface IAppointmentService {
    BaseResponseDTO<AppointmentResponseDTO> createNewAppointment(
        AppointmentRequestDTO appointmentRequestDTO);
}
