package com.capstone.alta.hms.api.v1.treatments.entities;

import com.capstone.alta.hms.api.v1.diagnoses.entities.Diagnose;
import com.capstone.alta.hms.api.v1.treatments.utils.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Treatment")
@Table(name = "treatments")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String report;

    @Column(nullable = false, length = 15)
    private Status status;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;
}
