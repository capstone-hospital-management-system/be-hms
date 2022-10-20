package com.capstone.alta.hms.api.v1.administrators.repositories;

import com.capstone.alta.hms.api.v1.administrators.entities.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Page<Administrator> findAll(Pageable pageable);
}