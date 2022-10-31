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
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientRequestDTO;
import com.capstone.alta.hms.api.v1.patients.dtos.PatientResponseDTO;
import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import com.capstone.alta.hms.api.v1.patients.repositories.PatientRepository;
import com.capstone.alta.hms.api.v1.patients.services.PatientService;
import com.capstone.alta.hms.api.v1.patients.utils.BloodType;
import com.capstone.alta.hms.api.v1.patients.utils.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(patientRepository.findById(any(Integer.class))).thenReturn(Optional.of(patient()));
        when(accountRepository.findById(any(Integer.class))).thenReturn(Optional.of(doctor()));
        when(clinicRepository.findById(any(Integer.class))).thenReturn(Optional.of(clinic()));
    }


    @Test
    public void createNewAppointment_shouldReturnNewCreatedAppointment() {

        Patient patient = new Patient();
        patient.setId(1);

        Appointment appointment = appointmentEntity();


        when(appointmentRespository.save(any(Appointment.class))).thenReturn(appointment);

        AppointmentResponseDTO appointmentResponseDTO = modelMapper.map(appointmentEntity(), AppointmentResponseDTO.class);
        appointmentResponseDTO.setAppointmentDate(appointment.getAppointmentDate());
        appointmentResponseDTO.setDoctor(modelMapper.map(doctor(), AccountResponseDTO.class));
        appointmentResponseDTO.setClinic(modelMapper.map(clinic(), ClinicResponseDTO.class));
        appointmentResponseDTO.setPatient(modelMapper.map(patient(), PatientResponseDTO.class));

        BaseResponseDTO<AppointmentResponseDTO> expected = new BaseResponseDTO("successfully creating data",
                appointmentResponseDTO);

        BaseResponseDTO<AppointmentResponseDTO> actual = appointmentService.createNewAppointment(appointmentRequestDTO());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getAllAppointments_shouldReturnListOfAppointmentsWithPagination() {
        int page = 1;
        int per_page = 1;

        Pageable pageable = PageRequest.of(page, per_page);

        Appointment appointment = appointmentEntity();

        Page<Appointment> appointmentsPage = new PageImpl<>(Collections.singletonList(appointment));

        when(appointmentRespository.findAll(pageable)).thenReturn(appointmentsPage);

        List<AppointmentResponseDTO> appointmentResponseDTOS = appointmentsPage.stream().map(appointmentPage -> modelMapper.map(
                appointmentPage, AppointmentResponseDTO.class)).collect(Collectors.toList());

        PageBaseResponseDTO<List<AppointmentResponseDTO>> expected = new PageBaseResponseDTO<>(
                "successfully retrieving data",
                new MetaResponseDTO(1, 1, 1, 1),
                appointmentResponseDTOS);

        PageBaseResponseDTO<List<AppointmentResponseDTO>> actual = appointmentService.getAllAppointments(pageable);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getAppointmentDetails_whenCurrentIdOfAppointmentExist_shouldReturnAppointment() {
        Appointment appointment = appointmentEntity();

        when(appointmentRespository.findById(appointment.getId())).thenReturn(Optional.of(appointment));

        AppointmentResponseDTO appointmentResponseDTO = modelMapper.map(appointment, AppointmentResponseDTO.class);

        BaseResponseDTO<AppointmentResponseDTO> expected = new BaseResponseDTO<>("success", appointmentResponseDTO);

        BaseResponseDTO<AppointmentResponseDTO> actual = appointmentService.getAppointmentDetails(appointment.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void updateAppointment_whenCurrentIdOfAppointmentExist_shouldReturnUpdatedAppointment() {
        Appointment appointmentUpdate = appointmentEntity();
        appointmentUpdate.setAppointmentDate(new Date());
        appointmentUpdate.setPatient(patient());
        appointmentUpdate.setDoctor(doctor());
        appointmentUpdate.setClinic(clinic());

        when(appointmentRespository.save(any(Appointment.class))).thenReturn(appointmentUpdate);

        AppointmentRequestDTO updatedAppointmentRequestDTO = modelMapper.map(appointmentUpdate, AppointmentRequestDTO.class);
        AppointmentResponseDTO updatedAppointmentResponseDTO = modelMapper.map(appointmentUpdate, AppointmentResponseDTO.class);

        BaseResponseDTO<AppointmentResponseDTO> expected = new BaseResponseDTO<>("successfully updating data",
                updatedAppointmentResponseDTO);


        BaseResponseDTO<AppointmentResponseDTO> actual = appointmentService.updateAppointment(appointmentUpdate.getId(),
                updatedAppointmentRequestDTO);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void deleteAppoinment_whenCurrentIdOfAppointmentExist_shouldReturnMessagesSuccessWithNullData() {
        Appointment appointment = appointmentEntity();

        BaseResponseDTO<AppointmentResponseDTO> expected = new BaseResponseDTO<>("successfully deleting data",
                null);

        BaseResponseDTO<AppointmentResponseDTO> actual = appointmentService.deleteAppointment(appointment.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }


    public Patient patient() {
        Patient patient = new Patient();
        patient.setId(1);
        return patient;
    }

    public Account doctor() {
        Account doctor = new Account();
        doctor.setId(1);
        return doctor;
    }


    public Clinic clinic() {
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
