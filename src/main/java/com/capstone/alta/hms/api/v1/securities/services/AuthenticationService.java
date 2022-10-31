package com.capstone.alta.hms.api.v1.securities.services;

import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.securities.dtos.AuthenticationRequestDTO;
import com.capstone.alta.hms.api.v1.securities.dtos.AuthenticationResponseDTO;
import com.capstone.alta.hms.api.v1.securities.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Override
    public BaseResponseDTO<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.getUsernameOrEmail(),
                authenticationRequestDTO.getPassword()
            ));

        SecurityContextHolder
            .getContext()
            .setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return new BaseResponseDTO<>(
            "success",
            new AuthenticationResponseDTO(jwt, authentication.getPrincipal())
        );
    }

}