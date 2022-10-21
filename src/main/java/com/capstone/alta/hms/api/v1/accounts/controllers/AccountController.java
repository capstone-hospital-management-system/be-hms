package com.capstone.alta.hms.api.v1.accounts.controllers;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.services.IAccountService;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<BaseResponseDTO<AccountResponseDTO>> createNewAccount(
            @RequestBody AccountRequestDTO accountRequestDTO) {
        BaseResponseDTO<AccountResponseDTO> response =
            accountService.createNewAccount(accountRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/accounts")
    public ResponseEntity<PageBaseResponseDTO<List<AccountResponseDTO>>> getAllAccounts(
            Pageable pageable) {
        PageBaseResponseDTO<List<AccountResponseDTO>> response =
            accountService.getAllAccounts(pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<BaseResponseDTO<AccountResponseDTO>> getAccountDetails(
            @PathVariable Integer id) {
        BaseResponseDTO<AccountResponseDTO> response =
            accountService.getAccountDetails(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<BaseResponseDTO<AccountResponseDTO>> updateAccount(
            @PathVariable Integer id,
            @RequestBody AccountRequestDTO accountRequestDTO) {
        BaseResponseDTO<AccountResponseDTO> response =
                accountService.updateAccount(id, accountRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<BaseResponseDTO<AccountResponseDTO>> deleteAccount(@PathVariable Integer id) {
        BaseResponseDTO response = accountService.deleteAccount(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
