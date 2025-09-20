package com.labops.service.impl;

import com.labops.dto.LabTestRequestDTO;
import com.labops.dto.LabTestResponseDTO;
import com.labops.dto.UpdateResultsRequestDTO;
import com.labops.entity.LabTest;
import com.labops.entity.TestTemplate;
import com.labops.entity.Visit;
import com.labops.repository.LabTestRepository;
import com.labops.repository.TestTemplateRepository;
import com.labops.repository.VisitRepository;
import com.labops.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LabTestServiceImpl implements LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private TestTemplateRepository testTemplateRepository;

    @Override
    public LabTestResponseDTO addTestToVisit(Long visitId, LabTestRequestDTO labTestRequestDTO) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        TestTemplate testTemplate = testTemplateRepository.findById(labTestRequestDTO.getTestTemplateId())
                .orElseThrow(() -> new RuntimeException("Test template not found"));

        LabTest labTest = new LabTest();
        labTest.setVisit(visit);
        labTest.setTestTemplate(testTemplate);
        labTest.setPrice(testTemplate.getBasePrice());
        labTest.setStatus("pending");

        labTest = labTestRepository.save(labTest);

        return convertToDTO(labTest);
    }

    @Override
    public LabTestResponseDTO updateResults(Long visitId, Long testId, UpdateResultsRequestDTO updateResultsRequestDTO) {
        LabTest labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
        // We could also check if the test belongs to the visit
        if (!labTest.getVisit().getVisitId().equals(visitId)) {
            throw new RuntimeException("Lab test does not belong to the specified visit");
        }
        labTest.setResults(updateResultsRequestDTO.getResults());
        labTest.setStatus("in-progress");
        labTest = labTestRepository.save(labTest);
        return convertToDTO(labTest);
    }

    @Override
    public LabTestResponseDTO approveResults(Long visitId, Long testId) {
        LabTest labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
        if (!labTest.getVisit().getVisitId().equals(visitId)) {
            throw new RuntimeException("Lab test does not belong to the specified visit");
        }
        labTest.setApproved(true);
        labTest.setApprovedAt(LocalDateTime.now());
        // In a real application, the approvedBy would be set from the security context
        labTest.setApprovedBy("admin"); // Placeholder
        labTest.setStatus("approved");


        labTest = labTestRepository.save(labTest);
        return convertToDTO(labTest);
    }

    private LabTestResponseDTO convertToDTO(LabTest labTest) {
        LabTestResponseDTO dto = new LabTestResponseDTO();
        dto.setTestId(labTest.getTestId());
        dto.setVisitId(labTest.getVisit().getVisitId());
        dto.setTestTemplateId(labTest.getTestTemplate().getTemplateId());
        dto.setStatus(labTest.getStatus());
        dto.setPrice(labTest.getPrice());
        dto.setResults(labTest.getResults());
        dto.setApproved(labTest.getApproved());
        dto.setApprovedBy(labTest.getApprovedBy());
        dto.setApprovedAt(labTest.getApprovedAt());
        return dto;
    }
}
