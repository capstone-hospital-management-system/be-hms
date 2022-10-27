package com.capstone.alta.hms.api.v1.securities.services;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.securities.entities.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> {
                logger.error("[POST] /api/v1/sign-in - username or email not found");
                return new UsernameNotFoundException(
                    "username or email not found : " + usernameOrEmail);
            });

        return UserPrincipal.create(account);
    }

    @Transactional
    public UserDetails loadUserById(Integer id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("account id not found");
                return new UsernameNotFoundException("account id not found : " + id);
            });

        return UserPrincipal.create(account);
    }
}