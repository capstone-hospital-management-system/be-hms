package com.capstone.alta.hms.api.v1.treatments.repositories;

import com.capstone.alta.hms.api.v1.treatments.entities.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    Page<Treatment> findAll(Pageable pageable);
}
