package com.capstone.alta.hms.api.v1.accounts.repositories;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Page<Account> findAll(Pageable pageable);

    Optional<Account> findByUsernameOrEmail(String username, String email);
}
