package com.labops.service;

import com.labops.dto.CultureSensitivityResultRequestDTO;
import com.labops.dto.CultureSensitivityResultResponseDTO;

import java.util.List;

public interface CultureSensitivityService {
    CultureSensitivityResultResponseDTO addOrUpdateCultureSensitivityResult(Long visitId, Long testId, CultureSensitivityResultRequestDTO requestDTO);
    List<CultureSensitivityResultResponseDTO> getCultureSensitivityResults(Long visitId, Long testId);
}
