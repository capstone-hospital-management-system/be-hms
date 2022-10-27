package com.capstone.alta.hms.api.v1.appointments.services;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
import com.capstone.alta.hms.api.v1.appointments.repositories.AppointmentRespository;
import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import com.capstone.alta.hms.api.v1.clinics.repositories.ClinicRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.capstone.alta.hms.api.v1.patients.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    PatientRepository patientRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<AppointmentResponseDTO> createNewAppointment(
        AppointmentRequestDTO appointmentRequestDTO) {

        Patient patient = patientRepository.findById(appointmentRequestDTO.getPatientId()).get();
        Account doctor = accountRepository.findById(appointmentRequestDTO.getDoctorId()).get();
        Clinic clinic = clinicRepository.findById(appointmentRequestDTO.getClinicId()).get();
        Account created_by = accountRepository.findById(appointmentRequestDTO.getCreatedBy()).get();

        Appointment newAppointment = new Appointment();
        newAppointment.setAppointmentDate(appointmentRequestDTO.getAppointmentDate());
        newAppointment.setPatient(patient);
        newAppointment.setDoctor(doctor);
        newAppointment.setClinic(clinic);
        newAppointment.setCreatedBy(created_by);
        newAppointment.setUpdatedBy(created_by);

        Appointment appointment = appointmentRespository.save(newAppointment);

        /*
        Appointment appointment = appointmentRespository.save(
            modelMapper.map(appointmentRequestDTO, Appointment.class)
        );
        */

        return new BaseResponseDTO<>(
            "201",
            HttpStatus.CREATED,
            "successfully creating data",
            modelMapper.map(appointment, AppointmentResponseDTO.class)
        );
    }
}
