package com.labops.service.impl;

import com.labops.dto.CultureSensitivityResultRequestDTO;
import com.labops.dto.CultureSensitivityResultResponseDTO;
import com.labops.entity.Antibiotic;
import com.labops.entity.CultureSensitivityResult;
import com.labops.entity.LabTest;
import com.labops.repository.AntibioticRepository;
import com.labops.repository.CultureSensitivityResultRepository;
import com.labops.repository.LabTestRepository;
import com.labops.service.CultureSensitivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CultureSensitivityServiceImpl implements CultureSensitivityService {

    @Autowired
    private CultureSensitivityResultRepository cultureSensitivityResultRepository;

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private AntibioticRepository antibioticRepository;

    @Override
    public CultureSensitivityResultResponseDTO addOrUpdateCultureSensitivityResult(Long visitId, Long testId, CultureSensitivityResultRequestDTO requestDTO) {
        LabTest labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
        if (!labTest.getVisit().getVisitId().equals(visitId)) {
            throw new RuntimeException("Lab test does not belong to the specified visit");
        }
        Antibiotic antibiotic = antibioticRepository.findById(requestDTO.getAntibioticId())
                .orElseThrow(() -> new RuntimeException("Antibiotic not found"));

        Optional<CultureSensitivityResult> existingResult = cultureSensitivityResultRepository.findByLabTestAndAntibiotic(labTest, antibiotic);

        CultureSensitivityResult result;
        if (existingResult.isPresent()) {
            // Update existing result
            result = existingResult.get();
            result.setSensitivity(requestDTO.getSensitivity());
        } else {
            // Create new result
            result = new CultureSensitivityResult();
            result.setLabTest(labTest);
            result.setVisit(labTest.getVisit());
            result.setAntibiotic(antibiotic);
            result.setSensitivity(requestDTO.getSensitivity());
        }

        result = cultureSensitivityResultRepository.save(result);

        return convertToDTO(result);
    }

    @Override
    public List<CultureSensitivityResultResponseDTO> getCultureSensitivityResults(Long visitId, Long testId) {
        LabTest labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
        if (!labTest.getVisit().getVisitId().equals(visitId)) {
            throw new RuntimeException("Lab test does not belong to the specified visit");
        }

        return cultureSensitivityResultRepository.findAllByLabTest(labTest).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CultureSensitivityResultResponseDTO convertToDTO(CultureSensitivityResult result) {
        CultureSensitivityResultResponseDTO dto = new CultureSensitivityResultResponseDTO();
        dto.setId(result.getId());
        dto.setVisitId(result.getVisit().getVisitId());
        dto.setTestId(result.getLabTest().getTestId());
        dto.setAntibioticId(result.getAntibiotic().getId());
        dto.setSensitivity(result.getSensitivity());
        return dto;
    }
}
