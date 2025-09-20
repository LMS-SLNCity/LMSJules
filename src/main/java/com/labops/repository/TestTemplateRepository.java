package com.labops.repository;

import com.labops.entity.TestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTemplateRepository extends JpaRepository<TestTemplate, Long> {
}
