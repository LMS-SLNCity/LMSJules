package com.labops.service;

import com.labops.dto.LabTestRequestDTO;
import com.labops.dto.LabTestResponseDTO;
import com.labops.dto.UpdateResultsRequestDTO;

public interface LabTestService {
    LabTestResponseDTO addTestToVisit(Long visitId, LabTestRequestDTO labTestRequestDTO);
    LabTestResponseDTO updateResults(Long visitId, Long testId, UpdateResultsRequestDTO updateResultsRequestDTO);
    LabTestResponseDTO approveResults(Long visitId, Long testId);
}
