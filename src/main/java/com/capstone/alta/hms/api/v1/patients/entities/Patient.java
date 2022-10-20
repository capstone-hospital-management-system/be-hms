package com.capstone.alta.hms.api.v1.patients.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Patient")
@Table(name = "patients")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String idCard;

    @Column(nullable = false, length = 3)
    private short age;

    @Column(nullable = false, length = 10)
    private String gender; //change this to enum latter

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 5)
    private String bloodType; //change this to enum latter

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 10)
    private String postalCode;

    @Column(nullable = false)
    private Date registerDate;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private Date bod;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}