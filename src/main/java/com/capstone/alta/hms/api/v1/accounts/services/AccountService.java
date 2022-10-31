package com.capstone.alta.hms.api.v1.accounts.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Autowired
    EntityManager em;

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
                "successfully retrieving data",
                new MetaResponseDTO(
                        accounts.getNumber() + 1,
                        accounts.getSize(),
                        accounts.getTotalPages(),
                        accounts.getTotalElements()
                ),
                accountResponseDTOS
            );
        }

        return new PageBaseResponseDTO<>(
            "data is empty",
            new MetaResponseDTO(
                    accounts.getNumber() + 1,
                    accounts.getSize(),
                    accounts.getTotalPages(),
                    accounts.getTotalElements()
            ),
            Collections.emptyList()
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> getAccountDetails(Integer id) {
        Account account = accountRepository.findById(id).get();
        return new BaseResponseDTO<>(
            "success",
            modelMapper.map(account, AccountResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> updateAccount(Integer id, AccountRequestDTO accountRequestDTO) {
        Account updateAccount = new Account();
        updateAccount.setId(id);
        updateAccount.setFirstName(accountRequestDTO.getFirstName());
        updateAccount.setLastName(accountRequestDTO.getLastName());
        updateAccount.setUsername(accountRequestDTO.getUsername());
        updateAccount.setEmail(accountRequestDTO.getEmail());
        updateAccount.setEmail(accountRequestDTO.getEmail());
        updateAccount.setPassword(passwordEncoder.encode(accountRequestDTO.getPassword()));
        updateAccount.setRole(accountRequestDTO.getRole());
        updateAccount.setIdCard(accountRequestDTO.getIdCard());
        updateAccount.setPhoneNumber(accountRequestDTO.getPhoneNumber());

        Account updatedAccount = accountRepository.save(updateAccount);

        return new BaseResponseDTO<>(
            "successfully updating data",
            modelMapper.map(updatedAccount, AccountResponseDTO.class)
        );
    }

    @Override
    public BaseResponseDTO<AccountResponseDTO> deleteAccount(Integer id) {
        accountRepository.deleteById(id);

        return new BaseResponseDTO<>(
            "successfully deleting data",
            null
        );
    }

    @Override
    public BaseResponseDTO<List<AccountResponseDTO>> getAccountsByRole(String role) {

        String jpql = "select a from Account a where a.role = :role";

        TypedQuery<Account> query = em.createQuery(jpql, Account.class);
        query.setParameter("role", Role.valueOf(String.valueOf(role)));

        List<Account> roleAccounts = query.getResultList();

        if (!roleAccounts.isEmpty()) {
            List<AccountResponseDTO> accountResponseDTOS = roleAccounts.stream()
                    .map(account -> modelMapper.map(account, AccountResponseDTO.class))
                    .collect(Collectors.toList());

            return new BaseResponseDTO<>(
                    "successfully retrieving data",
                    accountResponseDTOS
            );
        }
        return new BaseResponseDTO<>(
                "data is empty",
                Collections.emptyList()
        );

    }
}
