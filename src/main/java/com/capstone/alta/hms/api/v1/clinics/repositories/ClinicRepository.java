package com.capstone.alta.hms.api.v1.clinics.repositories;

import com.capstone.alta.hms.api.v1.clinics.entities.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    Page<Clinic> findAll(Pageable pageable);
}
