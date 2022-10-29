package com.capstone.alta.hms.api.v1.patients.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    PatientService patientService = spy(new PatientService());

    ModelMapper modelMapper = spy(new ModelMapper());

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createNewPatient_shouldReturnNewCreatedPatient() {
        Patient patient = patientEntity();

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientResponseDTO patientResponseDTO = modelMapper.map(patient, PatientResponseDTO.class);

        BaseResponseDTO<PatientResponseDTO> expected = new BaseResponseDTO("201", HttpStatus.CREATED,
                "successfully creating data", patientResponseDTO);

        BaseResponseDTO<PatientResponseDTO> actual = patientService.createNewPatient(patientRequestDTO());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getAllPatients_shouldReturnListOfPatientsWithPagination() {
        int page = 1;
        int per_page = 1;

        Pageable pageable = PageRequest.of(page, per_page);

        Patient patient = patientEntity();

        Page<Patient> patientsPage = new PageImpl<>(Collections.singletonList(patient));

        when(patientRepository.findAll(pageable)).thenReturn(patientsPage);

        List<PatientResponseDTO> patientsResponseDTO = patientsPage.stream().map(patientPage -> modelMapper.map(
                patientPage, PatientResponseDTO.class)).collect(Collectors.toList());

        PageBaseResponseDTO<List<PatientResponseDTO>> expected = new PageBaseResponseDTO<>("200", HttpStatus.OK,
                "successfully retrieving data", patientsResponseDTO, new MetaResponseDTO(1, 1,
                1, 1));

        PageBaseResponseDTO<List<PatientResponseDTO>> actual = patientService.getAllPatients(pageable);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getPatientDetails_whenCurrentIdOfPatientExist_shouldReturnPatient() {
        Patient patient = patientEntity();

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        PatientResponseDTO patientResponseDTO = modelMapper.map(patient, PatientResponseDTO.class);

        BaseResponseDTO<PatientResponseDTO> expected = new BaseResponseDTO<>("200", HttpStatus.OK,
                "success", patientResponseDTO);

        BaseResponseDTO<PatientResponseDTO> actual = patientService.getPatientDetails(patient.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void updatePatient_whenCurrentIdOfPatientExist_shouldReturnUpdatePatient() {
        Patient patientUpdate = patientEntity();
        patientUpdate.setFirstName("udin");
        patientUpdate.setLastName("suparman");
        patientUpdate.setIdCard("8472828");
        patientUpdate.setAge((short) 40);
        patientUpdate.setGender(Gender.FEMALE);
        patientUpdate.setAddress("jl. kapten udin");
        patientUpdate.setCity("depok");
        patientUpdate.setBloodType(BloodType.O);
        patientUpdate.setPhoneNumber("082828282");
        patientUpdate.setPostalCode("12345567");
        patientUpdate.setUsername("udin");
        patientUpdate.setBod(setDate(1667015122046L));
        patientUpdate.setRegisterBy(registrarAccount());
        patientUpdate.setUpdatedBy(updaterAccount());

        when(patientRepository.save(any(Patient.class))).thenReturn(patientUpdate);

        PatientRequestDTO updatedPatientRequestDTO = modelMapper.map(patientUpdate, PatientRequestDTO.class);
        PatientResponseDTO updatedPatientResponseDTO = modelMapper.map(patientUpdate, PatientResponseDTO.class);

        BaseResponseDTO<PatientResponseDTO> expected = new BaseResponseDTO<>("200", HttpStatus.OK,
                "successfully updating data", updatedPatientResponseDTO);


        BaseResponseDTO<PatientResponseDTO> actual = patientService.updatePatient(patientUpdate.getId(),
                updatedPatientRequestDTO);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void deletePatient_whenCurrentIdOfPatientExist_shouldReturnMessagesSuccessWithNullData() {
        Patient patient = patientEntity();

        BaseResponseDTO<PatientResponseDTO> expected = new BaseResponseDTO<>("200", HttpStatus.OK,
                "successfully deleting data", null);

        BaseResponseDTO<PatientResponseDTO> actual = patientService.deletePatient(patient.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

        verify(patientRepository).deleteById(patient.getId());
    }
    private PatientRequestDTO patientRequestDTO() {

        PatientRequestDTO patientRequestDTO = new PatientRequestDTO();
        patientRequestDTO.setFirstName("john");
        patientRequestDTO.setLastName("doe");
        patientRequestDTO.setIdCard("12343432");
        patientRequestDTO.setAge((short) 30);
        patientRequestDTO.setGender(Gender.MALE);
        patientRequestDTO.setAddress("jl. para developer");
        patientRequestDTO.setCity("bogor");
        patientRequestDTO.setBloodType(BloodType.B);
        patientRequestDTO.setPhoneNumber("08282828282");
        patientRequestDTO.setPostalCode("12345");
        patientRequestDTO.setUsername("john");
        patientRequestDTO.setBod(setDate(936868149000L));
        patientRequestDTO.setRegisterBy(registrarAccount());
        patientRequestDTO.setUpdatedBy(updaterAccount());

        return  patientRequestDTO;

    }

    private Patient patientEntity() {
        Patient patient = modelMapper.map(patientRequestDTO(), Patient.class);
        patient.setId(1);
        return patient;
    }

    private Account registrarAccount() {
        Account account = new Account();
        account.setId(1);
        return account;
    }

    private Account updaterAccount() {
        Account account = new Account();
        account.setId(2);
        return account;
    }

    private Date setDate(Long date) {
        return new Date(date);
    }
}
