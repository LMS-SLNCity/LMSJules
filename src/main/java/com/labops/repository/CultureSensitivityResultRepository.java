package com.labops.repository;

import com.labops.entity.CultureSensitivityResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labops.entity.Antibiotic;
import com.labops.entity.LabTest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CultureSensitivityResultRepository extends JpaRepository<CultureSensitivityResult, Long> {
    Optional<CultureSensitivityResult> findByLabTestAndAntibiotic(LabTest labTest, Antibiotic antibiotic);
    List<CultureSensitivityResult> findAllByLabTest(LabTest labTest);
}
