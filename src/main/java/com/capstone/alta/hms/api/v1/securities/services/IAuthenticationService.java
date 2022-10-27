package com.capstone.alta.hms.api.v1.securities.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.securities.dtos.AuthenticationRequestDTO;
import com.capstone.alta.hms.api.v1.securities.dtos.AuthenticationResponseDTO;

public interface IAuthenticationService {
    BaseResponseDTO<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authenticationRequestDTO);
}
