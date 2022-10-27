package com.capstone.alta.hms.api.v1.bills.repositories;

import com.capstone.alta.hms.api.v1.bills.entities.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
  Page<Bill> findAll(Pageable pageable);
}
