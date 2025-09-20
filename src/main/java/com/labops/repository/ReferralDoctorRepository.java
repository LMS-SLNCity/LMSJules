package com.labops.repository;

import com.labops.entity.ReferralDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralDoctorRepository extends JpaRepository<ReferralDoctor, Long> {
}
