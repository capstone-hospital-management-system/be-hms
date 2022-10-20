package com.capstone.alta.hms.api.v1.administrators.repositories;

import com.capstone.alta.hms.api.v1.administrators.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}