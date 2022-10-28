package com.capstone.alta.hms.api.v1.accounts.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public BaseResponseDTO<AccountResponseDTO> createNewAccount(
        AccountRequestDTO accountRequestDTO) {

        Account newAccount = new Account();
        newAccount.setFirstName(accountRequestDTO.getFirstName());
        newAccount.setLastName(accountRequestDTO.getLastName());
        newAccount.setUsername(accountRequestDTO.getUsername());
        newAccount.setEmail(accountRequestDTO.getEmail());
        newAccount.setEmail(accountRequestDTO.getEmail());
        newAccount.setPassword(passwordEncoder.encode(accountRequestDTO.getPassword()));
        newAccount.setRole(accountRequestDTO.getRole());
        newAccount.setIdCard(accountRequestDTO.getIdCard());
        newAccount.setPhoneNumber(accountRequestDTO.getPhoneNumber());

        Account account = accountRepository.save(newAccount);

        return new BaseResponseDTO<AccountResponseDTO>(
            "201",
            HttpStatus.CREATED,
            "successfully creating data",
            modelMapper.map(account, AccountResponseDTO.class)
        );
    }

    @Override
    public PageBaseResponseDTO<List<AccountResponseDTO>> getAllAccounts(Pageable pageable) {
        Page<Account> accounts = accountRepository.findAll(pageable);

        if (!accounts.isEmpty()) {
            List<AccountResponseDTO> accountResponseDTOS = accounts.stream()
                .map(account -> modelMapper.map(account, AccountResponseDTO.class))
                .collect(Collectors.toList());

            return new PageBaseResponseDTO<>(
                "200",
                HttpStatus.OK,
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

        return new PageBaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "data is empty",
            Collections.emptyList(),
            new MetaResponseDTO(
            accounts.getNumber() + 1,
                accounts.getSize(),
                accounts.getTotalPages(),
                accounts.getTotalElements()
            )
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> getAccountDetails(Integer id) {
        Account account = accountRepository.findById(id).get();
        return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "success",
            modelMapper.map(account, AccountResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> updateAccount(Integer id, AccountRequestDTO accountRequestDTO) {
        Account accountUpdate = modelMapper.map(accountRequestDTO, Account.class);
        accountUpdate.setId(id);

        Account account = accountRepository.save(accountUpdate);

        return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "successfully updating data",
            modelMapper.map(account, AccountResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> deleteAccount(Integer id) {
        accountRepository.deleteById(id);

        return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "successfully deleting data",
            null
        );
    }
}
