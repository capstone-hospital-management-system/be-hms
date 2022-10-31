package com.capstone.alta.hms.api.v1.clinics.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicRequestDTO;
import com.capstone.alta.hms.api.v1.clinics.dtos.ClinicResponseDTO;
import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import com.capstone.alta.hms.api.v1.clinics.repositories.ClinicRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClinicServiceTest {

    @Mock
    ClinicRepository clinicRepository;

    @InjectMocks
    ClinicService clinicService = spy(new ClinicService());

    ModelMapper modelMapper = spy(new ModelMapper());

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(clinicRepository.findById(any(Integer.class))).thenReturn(Optional.of(clinicEntity()));
    }

    @Test
    public void createNewClinic_shouldReturnNewCreatedClinic() {
        Clinic clinic = clinicEntity();

        when(clinicRepository.save(any(Clinic.class))).thenReturn(clinic);

        ClinicResponseDTO clinicResponseDTO = modelMapper.map(clinic, ClinicResponseDTO.class);

        BaseResponseDTO<ClinicResponseDTO> expected = new BaseResponseDTO("successfully creating data",
                clinicResponseDTO);

        BaseResponseDTO<ClinicResponseDTO> actual = clinicService.createNewClinic(clinicRequestDTO());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getAllClinics_shouldReturnListOfClinicsWithPagination() {
        int page = 1;
        int per_page = 1;

        Pageable pageable = PageRequest.of(page, per_page);

        Clinic clinic = clinicEntity();

        Page<Clinic> clinicsPage = new PageImpl<>(Collections.singletonList(clinic));

        when(clinicRepository.findAll(pageable)).thenReturn(clinicsPage);

        List<ClinicResponseDTO> clinicResponseDTOS = clinicsPage.stream().map(clinicPage -> modelMapper.map(
                clinicPage, ClinicResponseDTO.class)).collect(Collectors.toList());

        PageBaseResponseDTO<List<ClinicResponseDTO>> expected = new PageBaseResponseDTO<>(
                "successfully retrieving data",
                new MetaResponseDTO(1, 1, 1, 1),
                clinicResponseDTOS
        );

        PageBaseResponseDTO<List<ClinicResponseDTO>> actual = clinicService.getAllClinics(pageable);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getClinicDetails_whenCurrentIdOfClinicExist_shouldReturnClinic() {
        Clinic clinic = clinicEntity();

        when(clinicRepository.findById(clinic.getId())).thenReturn(Optional.of(clinic));

        ClinicResponseDTO clinicResponseDTO = modelMapper.map(clinic, ClinicResponseDTO.class);

        BaseResponseDTO<ClinicResponseDTO> expected = new BaseResponseDTO<>("successfully retrieving data", clinicResponseDTO);

        BaseResponseDTO<ClinicResponseDTO> actual = clinicService.getClinicDetails(clinic.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void updateClinic_whenCurrentIdOfClinicExist_shouldReturnUpdateClinic() {
        Clinic clinicUpdate = clinicEntity();
        clinicUpdate.setName("klinik gigi anak");
        clinicUpdate.setDescription("klinik gigi khusus anak ");

        ClinicRequestDTO updatedClinicRequestDTO = modelMapper.map(clinicUpdate, ClinicRequestDTO.class);
        ClinicResponseDTO updatedClinicResponseDTO = modelMapper.map(clinicUpdate, ClinicResponseDTO.class);

        BaseResponseDTO<ClinicResponseDTO> expected = new BaseResponseDTO<>("successfully updating data",
                updatedClinicResponseDTO);

        when(clinicRepository.save(any(Clinic.class))).thenReturn(clinicUpdate);

        BaseResponseDTO<ClinicResponseDTO> actual = clinicService.updateClinic(clinicUpdate.getId(),
                updatedClinicRequestDTO);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void deleteClinic_whenCurrentIdOfClinicExist_shouldReturnMessagesSuccessWithNullData() {
        Clinic clinic = clinicEntity();

        BaseResponseDTO<ClinicResponseDTO> expected = new BaseResponseDTO<>("successfully deleting data",
                null);

        BaseResponseDTO<ClinicResponseDTO> actual = clinicService.deleteClinic(clinic.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private ClinicRequestDTO clinicRequestDTO() {
        ClinicRequestDTO clinicRequestDTO = new ClinicRequestDTO();
        clinicRequestDTO.setName("klinik gigi");
        clinicRequestDTO.setDescription("klinik pengobatan masalah gigi");
        return clinicRequestDTO;
    }
    private Clinic clinicEntity() {
        Clinic clinic = new Clinic();
        clinic.setId(1);
        clinic.setName(clinicRequestDTO().getName());
        clinic.setDescription(clinicRequestDTO().getDescription());
        return clinic;
    }

}
