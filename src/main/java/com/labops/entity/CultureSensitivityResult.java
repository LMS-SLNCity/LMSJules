package com.labops.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "culture_sensitivity_results")
public class CultureSensitivityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private LabTest labTest;

    @ManyToOne
    @JoinColumn(name = "antibiotic_id", nullable = false)
    private Antibiotic antibiotic;

    private String sensitivity;
}
