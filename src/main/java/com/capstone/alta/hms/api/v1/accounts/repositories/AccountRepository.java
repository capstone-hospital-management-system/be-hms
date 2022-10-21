package com.capstone.alta.hms.api.v1.accounts.repositories;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
