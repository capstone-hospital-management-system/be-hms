package com.capstone.alta.hms.api.v1.accounts.controllers;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.dtos.AccountResponseDTO;
import com.capstone.alta.hms.api.v1.accounts.services.IAccountService;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
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

        if (response.getCode().equals("201")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/accounts")
    public ResponseEntity<BaseResponseDTO<List<AccountResponseDTO>>> getAllAccounts(
            Pageable pageable) {
        BaseResponseDTO<List<AccountResponseDTO>> response =
            accountService.getAllAccounts(pageable);

        if (response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<BaseResponseDTO<AccountResponseDTO>> getAccountDetails(
            @PathVariable Integer id) {
        BaseResponseDTO<AccountResponseDTO> response =
            accountService.getAccountDetails(id);

        if (response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<BaseResponseDTO<AccountResponseDTO>> updateAccount(
            @PathVariable Integer id,
            @RequestBody AccountRequestDTO accountRequestDTO) {
        BaseResponseDTO<AccountResponseDTO> response =
                accountService.updateAccount(id, accountRequestDTO);

        if (response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<BaseResponseDTO<AccountResponseDTO>> deleteAccount(@PathVariable Integer id) {
        BaseResponseDTO response = accountService.deleteAccount(id);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
