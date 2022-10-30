package com.capstone.alta.hms.api.v1.accounts.entities;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Account")
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private Role role;

    @Column(nullable = false, length = 100)
    private String idCard;

    private String others;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(
        mappedBy = "doctor",
        orphanRemoval = true
    )
    private List<Appointment> appointments = new ArrayList<>();
}
