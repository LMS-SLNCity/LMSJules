package com.labops.service;

import com.labops.dto.VisitRequestDTO;
import com.labops.dto.VisitResponseDTO;

import java.util.List;

public interface VisitService {
    VisitResponseDTO createVisit(VisitRequestDTO visitRequestDTO);
    VisitResponseDTO getVisitById(Long id);
    List<VisitResponseDTO> getAllVisits(String status);
}
