package com.capstone.alta.hms.api.v1.prescriptions.repositories;

import com.capstone.alta.hms.api.v1.prescriptions.entities.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
  Page<Prescription> findAll(Pageable pageable);
}
