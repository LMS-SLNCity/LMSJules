package com.labops.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long visitId;

    private String salutation;

    private String name;

    @Column(name = "age_years")
    private Integer ageYears;

    @Column(name = "age_months")
    private Integer ageMonths;

    @Column(name = "age_days")
    private Integer ageDays;

    private String sex;

    private String phone;

    private String address;

    @Column(name = "date_of_visit")
    private LocalDate dateOfVisit;

    @ManyToOne
    @JoinColumn(name = "referred_doctor_id")
    private ReferralDoctor referredDoctor;

    @Column(name = "visit_code", unique = true)
    private String visitCode;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    private String status;
}
