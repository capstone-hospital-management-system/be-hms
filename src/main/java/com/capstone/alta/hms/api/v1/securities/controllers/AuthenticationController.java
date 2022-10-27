package com.capstone.alta.hms.api.v1.securities.controllers;

import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.securities.dtos.AuthenticationRequestDTO;
import com.capstone.alta.hms.api.v1.securities.dtos.AuthenticationResponseDTO;
import com.capstone.alta.hms.api.v1.securities.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    @Autowired
    IAuthenticationService authenticationService;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/auth/sign-in")
    public ResponseEntity<BaseResponseDTO<AuthenticationResponseDTO>> signIn(
        @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {

        BaseResponseDTO<AuthenticationResponseDTO> response =
            authenticationService.authenticate(authenticationRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
