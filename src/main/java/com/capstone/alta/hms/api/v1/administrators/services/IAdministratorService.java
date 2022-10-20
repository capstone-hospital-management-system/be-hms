package com.capstone.alta.hms.api.v1.administrators.services;

import com.capstone.alta.hms.api.v1.administrators.dtos.AdministratorRequestDTO;
import com.capstone.alta.hms.api.v1.administrators.dtos.AdministratorResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;

public interface IAdministratorService {
    BaseResponseDTO<AdministratorResponseDTO> craeteNewAdministrator(
            AdministratorRequestDTO administratorRequestDTO);
    
}
