package com.labops.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "antibiotics")
public class Antibiotic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
