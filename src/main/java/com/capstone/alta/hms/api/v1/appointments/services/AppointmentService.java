package com.capstone.alta.hms.api.v1.appointments.services;

import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
import com.capstone.alta.hms.api.v1.appointments.repositories.AppointmentRespository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService{
    @Autowired
    AppointmentRespository appointmentRespository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<AppointmentResponseDTO> createNewAppointment(
        Integer accountId,
        AppointmentRequestDTO appointmentRequestDTO) {

        Optional<Appointment> appointment = accountRepository.findById(accountId)
                .map(account -> {
                    appointmentRequestDTO.setCreatedBy(account);
                    appointmentRequestDTO.setUpdatedBy(account);
                    return appointmentRespository.save(modelMapper.map(appointmentRequestDTO, Appointment.class));
                });

        return new BaseResponseDTO<>(
                "201",
                HttpStatus.CREATED,
                "successfully creating data",
                modelMapper.map(appointment.get(), AppointmentResponseDTO.class)
        );
    }
}
