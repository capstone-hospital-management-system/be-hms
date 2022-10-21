package com.capstone.alta.hms.api.v1.accounts.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<AccountResponseDTO> createNewAccount(
        AccountRequestDTO accountRequestDTO) {
        Account account = accountRepository.save(
            modelMapper.map(accountRequestDTO, Account.class)
        );

        if (account != null) {
            AccountResponseDTO accountResponseDTO =
                modelMapper.map(account, AccountResponseDTO.class);
            return new BaseResponseDTO<AccountResponseDTO>(
                "201",
                "CREATED",
                "successfully creating data",
                accountResponseDTO
            );
        }

        return new BaseResponseDTO<AccountResponseDTO>(
            "422",
            HttpStatus.UNPROCESSABLE_ENTITY.toString(),
            "error",
            null
        );
    }

    @Override
    public BaseResponseDTO<List<AccountResponseDTO>> getAllAccounts(Pageable pageable) {
        Page<Account> accounts = accountRepository.findAll(pageable);

        if (!accounts.isEmpty()) {
            List<AccountResponseDTO> accountResponseDTOS = accounts.stream()
                .map(account -> modelMapper.map(account, AccountResponseDTO.class))
                .collect(Collectors.toList());

            return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK.toString(),
                "successfully retrieving data",
                accountResponseDTOS,
                new MetaResponseDTO(
                    accounts.getNumber() + 1,
                    accounts.getSize(),
                    accounts.getTotalPages(),
                    accounts.getTotalElements()
                )
            );
        }

        return new BaseResponseDTO<>(
            "204",
            HttpStatus.NO_CONTENT.toString(),
            "data is empty",
            Collections.emptyList()
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> getAccountDetails(Integer id) {
        Account account = accountRepository.findById(id).get();

        if (account != null) {
            return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK.toString(),
                "success",
                modelMapper.map(account, AccountResponseDTO.class)
            );
        }

        return new BaseResponseDTO<>(
                "404",
                HttpStatus.NOT_FOUND.toString(),
                "data is empty",
                new AccountResponseDTO()
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> updateAccount(Integer id, AccountRequestDTO accountRequestDTO) {
        Account accountUpdate = modelMapper.map(accountRequestDTO, Account.class);
        accountUpdate.setId(id);

        Account account = accountRepository.save(accountUpdate);
        if (account.equals(accountUpdate)) {
            return new BaseResponseDTO<>(
                "200",
                HttpStatus.OK.toString(),
                "successfully updating data",
                modelMapper.map(account, AccountResponseDTO.class)
            );
        }

        return new BaseResponseDTO<>(
                "422",
                HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                "failed to update data",
                new AccountResponseDTO()
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> deleteAccount(Integer id) {
        accountRepository.deleteById(id);

        return new BaseResponseDTO<>(
            "204",
            HttpStatus.NO_CONTENT.toString(),
            "successfully deleting data",
            new AccountResponseDTO()
        );
    }
}
