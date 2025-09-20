package com.labops.service.impl;

import com.labops.dto.VisitRequestDTO;
import com.labops.dto.VisitResponseDTO;
import com.labops.entity.ReferralDoctor;
import com.labops.entity.Visit;
import com.labops.repository.ReferralDoctorRepository;
import com.labops.repository.VisitRepository;
import com.labops.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private ReferralDoctorRepository referralDoctorRepository;

    @Override
    public VisitResponseDTO createVisit(VisitRequestDTO visitRequestDTO) {
        Visit visit = new Visit();
        // Manual mapping from DTO to entity
        visit.setSalutation(visitRequestDTO.getSalutation());
        visit.setName(visitRequestDTO.getName());
        visit.setAgeYears(visitRequestDTO.getAgeYears());
        visit.setAgeMonths(visitRequestDTO.getAgeMonths());
        visit.setAgeDays(visitRequestDTO.getAgeDays());
        visit.setSex(visitRequestDTO.getSex());
        visit.setPhone(visitRequestDTO.getPhone());
        visit.setAddress(visitRequestDTO.getAddress());
        visit.setDateOfVisit(visitRequestDTO.getDateOfVisit());
        visit.setVisitCode(visitRequestDTO.getVisitCode());
        visit.setStatus(visitRequestDTO.getStatus());

        if (visitRequestDTO.getReferredDoctorId() != null) {
            ReferralDoctor doctor = referralDoctorRepository.findById(visitRequestDTO.getReferredDoctorId())
                    .orElseThrow(() -> new RuntimeException("Referral doctor not found"));
            visit.setReferredDoctor(doctor);
        }

        visit = visitRepository.save(visit);

        return convertToDTO(visit);
    }

    @Override
    public VisitResponseDTO getVisitById(Long id) {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new RuntimeException("Visit not found"));
        return convertToDTO(visit);
    }

    @Override
    public List<VisitResponseDTO> getAllVisits(String status) {
        List<Visit> visits;
        if (status != null && !status.isEmpty()) {
            visits = visitRepository.findAllByStatus(status);
        } else {
            visits = visitRepository.findAll();
        }
        return visits.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private VisitResponseDTO convertToDTO(Visit visit) {
        VisitResponseDTO dto = new VisitResponseDTO();
        dto.setVisitId(visit.getVisitId());
        dto.setSalutation(visit.getSalutation());
        dto.setName(visit.getName());
        dto.setAgeYears(visit.getAgeYears());
        dto.setAgeMonths(visit.getAgeMonths());
        dto.setAgeDays(visit.getAgeDays());
        dto.setSex(visit.getSex());
        dto.setPhone(visit.getPhone());
        dto.setAddress(visit.getAddress());
        dto.setDateOfVisit(visit.getDateOfVisit());
        if (visit.getReferredDoctor() != null) {
            dto.setReferredDoctorId(visit.getReferredDoctor().getDoctorId());
        }
        dto.setVisitCode(visit.getVisitCode());
        dto.setCreatedAt(visit.getCreatedAt());
        dto.setStatus(visit.getStatus());
        return dto;
    }
}
