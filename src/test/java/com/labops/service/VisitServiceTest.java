package com.labops.service;

import com.labops.dto.VisitRequestDTO;
import com.labops.dto.VisitResponseDTO;
import com.labops.entity.ReferralDoctor;
import com.labops.entity.Visit;
import com.labops.repository.ReferralDoctorRepository;
import com.labops.repository.VisitRepository;
import com.labops.service.impl.VisitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @Mock
    private ReferralDoctorRepository referralDoctorRepository;

    @InjectMocks
    private VisitServiceImpl visitService;

    private VisitRequestDTO visitRequestDTO;
    private Visit visit;
    private ReferralDoctor referralDoctor;

    @BeforeEach
    void setUp() {
        referralDoctor = new ReferralDoctor();
        referralDoctor.setDoctorId(1L);
        referralDoctor.setName("Dr. Smith");

        visitRequestDTO = new VisitRequestDTO();
        visitRequestDTO.setName("John Doe");
        visitRequestDTO.setReferredDoctorId(1L);

        visit = new Visit();
        visit.setVisitId(1L);
        visit.setName("John Doe");
        visit.setReferredDoctor(referralDoctor);
    }

    @Test
    void testCreateVisit() {
        when(referralDoctorRepository.findById(1L)).thenReturn(Optional.of(referralDoctor));
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        VisitResponseDTO visitResponseDTO = visitService.createVisit(visitRequestDTO);

        assertEquals("John Doe", visitResponseDTO.getName());
        assertEquals(1L, visitResponseDTO.getReferredDoctorId());
    }
}
