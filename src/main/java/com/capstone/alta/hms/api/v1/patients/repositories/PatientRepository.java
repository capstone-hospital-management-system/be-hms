package com.capstone.alta.hms.api.v1.patients.repositories;

import com.capstone.alta.hms.api.v1.patients.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
