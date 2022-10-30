package com.capstone.alta.hms.api.v1.diagnoses.entities;

import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
import com.capstone.alta.hms.api.v1.prescriptions.entities.Prescription;
import com.capstone.alta.hms.api.v1.treatments.entities.Treatment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Diagnose")
@Table(name = "diagnoses")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String report;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @OneToOne(mappedBy = "diagnose")
    private Treatment treatment;

    @OneToOne(mappedBy = "diagnose")
    private Prescription prescription;
}
