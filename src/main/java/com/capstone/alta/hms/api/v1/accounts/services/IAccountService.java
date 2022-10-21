package com.capstone.alta.hms.api.v1.accounts.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    BaseResponseDTO<AccountResponseDTO> createNewAccount(AccountRequestDTO accountRequestDTO);
    BaseResponseDTO<List<AccountResponseDTO>> getAllAccounts(Pageable pageable);
    BaseResponseDTO<AccountResponseDTO> getAccountDetails(Integer id);
    BaseResponseDTO<AccountResponseDTO> updateAccount(Integer id, AccountRequestDTO accountRequestDTO);
}
