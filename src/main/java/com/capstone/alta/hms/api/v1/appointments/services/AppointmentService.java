package com.capstone.alta.hms.api.v1.appointments.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
import com.capstone.alta.hms.api.v1.appointments.repositories.AppointmentRespository;
import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import com.capstone.alta.hms.api.v1.clinics.repositories.ClinicRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.capstone.alta.hms.api.v1.patients.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageBaseResponseDTO<List<AppointmentResponseDTO>> getAllAppointments(Pageable pageable) {
        Page<Appointment> appointments = appointmentRespository.findAll(pageable);

        if (!appointments.isEmpty()) {
            List<AppointmentResponseDTO> appointmentResponseDTOS = appointments.stream()
                    .map(appointment -> modelMapper.map(appointment, AppointmentResponseDTO.class))
                    .collect(Collectors.toList());

            return new PageBaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully retrieving data",
                appointmentResponseDTOS,
                new MetaResponseDTO(
                    appointments.getNumber() + 1,
                    appointments.getSize(),
                    appointments.getTotalPages(),
                    appointments.getTotalElements()
                )
            );
        }

        return new PageBaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "data is empty",
            Collections.emptyList(),
            new MetaResponseDTO(
                appointments.getNumber() + 1,
                appointments.getSize(),
                appointments.getTotalPages(),
                appointments.getTotalElements()
            )
        );
    }

    @Override
    public BaseResponseDTO<AppointmentResponseDTO> getAppointmentDetails(Integer id) {
        Appointment appointment = appointmentRespository.findById(id).get();
        return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "success",
                modelMapper.map(appointment, AppointmentResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<AppointmentResponseDTO> updateAppointment(
        Integer id, AppointmentRequestDTO appointmentRequestDTO) {

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

        Appointment updatedAppointment = appointmentRespository.save(newAppointment);

        return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully updating data",
                modelMapper.map(updatedAppointment, AppointmentResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<AppointmentResponseDTO> deleteAppointment(Integer id) {
        appointmentRespository.deleteById(id);

        return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK,
                "successfully deleting data",
                null
        );
    }
}
