package com.capstone.alta.hms.api.v1.core.runners;

import com.capstone.alta.hms.api.v1.accounts.dtos.AccountRequestDTO;
import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.repositories.AccountRepository;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AccountRunner.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Value("${DEFAULT_ADMIN_USERNAME}")
    private String username;

    @Value("${DEFAULT_ADMIN_EMAIL}")
    private String email;

    @Value("${DEFAULT_ADMIN_PASSWORD}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        if (accountRepository.findAll().isEmpty()) {
            AccountRequestDTO newAccount = new AccountRequestDTO();

            newAccount.setFirstName("super");
            newAccount.setLastName("admin");
            newAccount.setUsername(username);
            newAccount.setEmail(email);
            newAccount.setPassword(passwordEncoder.encode(password));
            newAccount.setRole(Role.ADMINISTRATOR);
            newAccount.setIdCard("123456789");
            newAccount.setPhoneNumber("0123456789");

            accountRepository.save(modelMapper.map(newAccount, Account.class));

            logger.info("Default administrator created");

        }
    }
}
