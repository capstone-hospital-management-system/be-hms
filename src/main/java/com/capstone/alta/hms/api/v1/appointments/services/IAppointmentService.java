package com.capstone.alta.hms.api.v1.appointments.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAppointmentService {
    BaseResponseDTO<AppointmentResponseDTO> createNewAppointment(
        AppointmentRequestDTO appointmentRequestDTO);
    PageBaseResponseDTO<List<AppointmentResponseDTO>> getAllAppointments(Pageable pageable);
    BaseResponseDTO<AppointmentResponseDTO> getAppointmentDetails(Integer id);
    BaseResponseDTO<AppointmentResponseDTO> updateAppointment(Integer id, AppointmentRequestDTO appointmentRequestDTO);
    BaseResponseDTO<AppointmentResponseDTO> deleteAppointment(Integer id);
}
