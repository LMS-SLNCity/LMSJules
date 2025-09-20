package com.labops.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitRequestDTO {
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
    private String status;
}
