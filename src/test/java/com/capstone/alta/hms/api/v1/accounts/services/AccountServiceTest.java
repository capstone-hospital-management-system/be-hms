package com.capstone.alta.hms.api.v1.accounts.services;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    AccountService accountService = spy(new AccountService());

    ModelMapper modelMapper = spy(new ModelMapper());

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createNewAccount_shouldReturnNewCreatedAccount() {
        Account account = accountEntity();

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponseDTO accountResponseDTO = modelMapper.map(account, AccountResponseDTO.class);

        BaseResponseDTO<AccountResponseDTO> expected = new BaseResponseDTO("successfully creating data",
                accountResponseDTO);

        BaseResponseDTO<AccountResponseDTO> actual = accountService.createNewAccount(accountRequestDTO());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getAllAccounts_shouldReturnListOfAccountsWithPagination() {
        int page = 1;
        int per_page = 1;

        Pageable pageable = PageRequest.of(page, per_page);

        Account account = accountEntity();

        Page<Account> accountsPage = new PageImpl<>(Collections.singletonList(account));

        when(accountRepository.findAll(pageable)).thenReturn(accountsPage);

        List<AccountResponseDTO> accountsResponseDTO = accountsPage.stream().map(accountPage -> modelMapper.map(
                accountPage, AccountResponseDTO.class)).collect(Collectors.toList());

        PageBaseResponseDTO<List<AccountResponseDTO>> expected = new PageBaseResponseDTO<>(
                "successfully retrieving data",
                new MetaResponseDTO(1, 1, 1, 1),
                accountsResponseDTO
            );

        PageBaseResponseDTO<List<AccountResponseDTO>> actual = accountService.getAllAccounts(pageable);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getAccountDetails_whenCurrentIdOfAccountExist_shouldReturnAccount() {
        Account account = accountEntity();

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        AccountResponseDTO accountResponseDTO = modelMapper.map(account, AccountResponseDTO.class);

        BaseResponseDTO<AccountResponseDTO> expected = new BaseResponseDTO<>("success", accountResponseDTO);

        BaseResponseDTO<AccountResponseDTO> actual = accountService.getAccountDetails(account.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void updateAccount_whenCurrentIdOfAccountExist_shouldReturnUpdateAccount() {
        Account accountUpdate = accountEntity();
        accountUpdate.setFirstName("ujang");
        accountUpdate.setLastName("charles");
        accountUpdate.setUsername("ujang");
        accountUpdate.setEmail("ujang@email.com");
        accountUpdate.setPassword("password");
        accountUpdate.setRole(Role.RECEPTIONIST);
        accountUpdate.setIdCard("12342432432");
        accountUpdate.setPhoneNumber("098989888888");

        AccountRequestDTO updatedAccountRequestDTO = modelMapper.map(accountUpdate, AccountRequestDTO.class);
        AccountResponseDTO updatedAccountResponseDTO = modelMapper.map(accountUpdate, AccountResponseDTO.class);

        BaseResponseDTO<AccountResponseDTO> expected = new BaseResponseDTO<>("successfully updating data",
                updatedAccountResponseDTO);

        when(accountRepository.save(any(Account.class))).thenReturn(accountUpdate);

        BaseResponseDTO<AccountResponseDTO> actual = accountService.updateAccount(accountUpdate.getId(),
                updatedAccountRequestDTO);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void deleteAccount_whenCurrentIdOfAccountExist_shouldReturnMessagesSuccessWithNullData() {
        Account account = accountEntity();

        BaseResponseDTO<AccountResponseDTO> expected = new BaseResponseDTO<>("successfully deleting data",
                null);

        BaseResponseDTO<AccountResponseDTO> actual = accountService.deleteAccount(account.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

        verify(accountRepository).deleteById(account.getId());
    }

    /**
    @Test
    public void getAccountsByRole_whenRoleExist_shouldReturnListOfAccountsWithGivenRole() {

        String jpql = "select a from Account a where a.role = :role";

        AccountResponseDTO accountResponseDTO = modelMapper.map(accountEntity(), AccountResponseDTO.class);

        BaseResponseDTO<List<AccountResponseDTO>> expected = new BaseResponseDTO<>("200", HttpStatus.OK,
                "successfully retrieving data", List.of(accountResponseDTO) );

        BaseResponseDTO<List<AccountResponseDTO>> actual = accountService.getAccountsByRole("DOCTOR");

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }
     */


    private AccountRequestDTO accountRequestDTO() {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setFirstName("john");
        accountRequestDTO.setLastName("doe");
        accountRequestDTO.setUsername("johndoe");
        accountRequestDTO.setEmail("jondoe@jondoe.com");
        accountRequestDTO.setPassword("password");
        accountRequestDTO.setRole(Role.DOCTOR);
        accountRequestDTO.setIdCard("12343423432");
        accountRequestDTO.setPhoneNumber("1234432432432");
        return accountRequestDTO;
    }

    private Account accountEntity() {
        Account account = new Account();
        account.setFirstName(accountRequestDTO().getFirstName());
        account.setLastName(accountRequestDTO().getLastName());
        account.setUsername(accountRequestDTO().getUsername());
        account.setEmail(accountRequestDTO().getEmail());
        account.setEmail(accountRequestDTO().getEmail());
        account.setPassword(passwordEncoder.encode(accountRequestDTO().getPassword()));
        account.setRole(accountRequestDTO().getRole());
        account.setIdCard(accountRequestDTO().getIdCard());
        account.setPhoneNumber(accountRequestDTO().getPhoneNumber());
        account.setId(1);
        return account;
    }
}
