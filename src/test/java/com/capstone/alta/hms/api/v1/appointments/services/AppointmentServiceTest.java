package com.capstone.alta.hms.api.v1.appointments.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
import com.capstone.alta.hms.api.v1.appointments.repositories.AppointmentRespository;
import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicResponseDTO;
import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import com.capstone.alta.hms.api.v1.clinics.repositories.ClinicRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.capstone.alta.hms.api.v1.patients.repositories.PatientRepository;
import com.capstone.alta.hms.api.v1.patients.services.PatientService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {
    @Mock
    AppointmentRespository appointmentRespository;


    @Mock
    PatientRepository patientRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    ClinicRepository clinicRepository;

    ModelMapper modelMapper = spy(new ModelMapper());

    @InjectMocks
    AppointmentService appointmentService = spy(new AppointmentService());

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(accountRepository.findById(1)).thenReturn(Optional.of(doctor()));
        when(clinicRepository.findById(1)).thenReturn(Optional.of(clinic()));
    }


    @Test
    public void createNewAppointment_shouldReturnNewCreatedAppointment() {

        Patient patient = new Patient();
        patient.setId(1);

        Appointment appointment = appointmentEntity();

        when(patientRepository.findById(any(Integer.class))).thenReturn(Optional.of(patient));

        when(appointmentRespository.save(any(Appointment.class))).thenReturn(appointment);

        AppointmentResponseDTO appointmentResponseDTO = new AppointmentResponseDTO();
        appointmentResponseDTO.setAppointmentDate(appointment.getAppointmentDate());
        appointmentResponseDTO.setDoctor(modelMapper.map(doctor(), AccountResponseDTO.class));
        appointmentResponseDTO.setClinic(modelMapper.map(clinic(), ClinicResponseDTO.class));
        appointmentResponseDTO.setPatient(modelMapper.map(patient(), PatientResponseDTO.class));

        BaseResponseDTO<AppointmentResponseDTO> expected = new BaseResponseDTO("201", HttpStatus.CREATED,
                "successfully creating data", appointmentResponseDTO);

        BaseResponseDTO<AppointmentResponseDTO> actual = appointmentService.createNewAppointment(appointmentRequestDTO());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }


    public Patient patient() {
        Patient patient = new Patient();
        patient.setId(1);
        return patient;
    }

    private Account doctor() {
        Account doctor = new Account();
        doctor.setId(1);
        return doctor;
    }


    private Clinic clinic() {
        Clinic clinic = new Clinic();
        clinic.setId(1);
        return clinic;
    }

    private AppointmentRequestDTO appointmentRequestDTO() {
        AppointmentRequestDTO appointmentRequestDTO = new AppointmentRequestDTO();
        appointmentRequestDTO.setAppointmentDate(new Date());
        appointmentRequestDTO.setDoctorId(doctor().getId());
        appointmentRequestDTO.setPatientId(patient().getId());
        appointmentRequestDTO.setClinicId(clinic().getId());

        return appointmentRequestDTO;

    }

    private Appointment appointmentEntity() {
        Appointment appointment = new Appointment();
        appointment.setId(1);
        appointment.setAppointmentDate(appointmentRequestDTO().getAppointmentDate());
        appointment.setDoctor(doctor());
        appointment.setClinic(clinic());
        appointment.setPatient(patient());

        return appointment;

    }
}
