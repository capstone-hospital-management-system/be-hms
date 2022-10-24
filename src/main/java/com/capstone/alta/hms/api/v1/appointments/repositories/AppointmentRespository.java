package com.capstone.alta.hms.api.v1.appointments.repositories;

import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRespository extends JpaRepository<Appointment, Integer> {
}
