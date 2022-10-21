package com.capstone.alta.hms.api.v1.accounts.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;

public interface IAccountService {
    BaseResponseDTO<AccountResponseDTO> createNewAccount(
        AccountRequestDTO accountRequestDTO
    );
}
