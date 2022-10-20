package com.capstone.alta.hms.api.v1.administrators.services;

import com.capstone.alta.hms.api.v1.administrators.dtos.AdministratorRequestDTO;
import com.capstone.alta.hms.api.v1.administrators.dtos.AdministratorResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdministratorService {
    BaseResponseDTO<AdministratorResponseDTO> createNewAdministrator(
            AdministratorRequestDTO administratorRequestDTO);
    BaseResponseDTO<List<AdministratorResponseDTO>> getAllAdministrators(Pageable pageable);
    BaseResponseDTO<AdministratorResponseDTO> getAdministratorDetails(Long id);


}
