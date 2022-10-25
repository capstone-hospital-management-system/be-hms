package com.capstone.alta.hms.api.v1.diagnoses.repositories;

import com.capstone.alta.hms.api.v1.diagnoses.entities.Diagnose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, Integer> {
    Page<Diagnose> findAll(Pageable pageable);
}
