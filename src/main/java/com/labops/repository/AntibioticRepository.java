package com.labops.repository;

import com.labops.entity.Antibiotic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntibioticRepository extends JpaRepository<Antibiotic, Long> {
}
