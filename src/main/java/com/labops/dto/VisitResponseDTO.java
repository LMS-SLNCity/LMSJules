package com.labops.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VisitResponseDTO {
    private Long visitId;
    private String salutation;
    private String name;
    private Integer ageYears;
    private Integer ageMonths;
    private Integer ageDays;
    private String sex;
    private String phone;
    private String address;
    private LocalDate dateOfVisit;
    private Long referredDoctorId;
    private String visitCode;
    private LocalDateTime createdAt;
    private String status;
}
