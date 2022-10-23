package com.capstone.alta.hms.api.v1.medicines.repositories;

import com.capstone.alta.hms.api.v1.medicines.entities.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
  Page<Medicine> findAll(Pageable pageable);
}
