package com.capstone.alta.hms.api.v1.accounts.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    BaseResponseDTO<AccountResponseDTO> createNewAccount(AccountRequestDTO accountRequestDTO);
    PageBaseResponseDTO<List<AccountResponseDTO>> getAllAccounts(Pageable pageable);
    BaseResponseDTO<AccountResponseDTO> getAccountDetails(Integer id);
    BaseResponseDTO<AccountResponseDTO> updateAccount(Integer id, AccountRequestDTO accountRequestDTO);
    BaseResponseDTO<AccountResponseDTO> deleteAccount(Integer id);
    BaseResponseDTO<List<AccountResponseDTO>> getAccountsByRole(String role);
}
