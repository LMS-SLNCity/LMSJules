package com.labops.service.impl;

import com.labops.dto.TestTemplateRequestDTO;
import com.labops.dto.TestTemplateResponseDTO;
import com.labops.entity.TestTemplate;
import com.labops.repository.TestTemplateRepository;
import com.labops.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestTemplateServiceImpl implements TestTemplateService {

    @Autowired
    private TestTemplateRepository testTemplateRepository;

    @Override
    public TestTemplateResponseDTO createTestTemplate(TestTemplateRequestDTO testTemplateRequestDTO) {
        TestTemplate testTemplate = new TestTemplate();
        testTemplate.setName(testTemplateRequestDTO.getName());
        testTemplate.setDescription(testTemplateRequestDTO.getDescription());
        testTemplate.setParameters(testTemplateRequestDTO.getParameters());
        testTemplate.setBasePrice(testTemplateRequestDTO.getBasePrice());

        testTemplate = testTemplateRepository.save(testTemplate);

        return convertToDTO(testTemplate);
    }

    @Override
    public List<TestTemplateResponseDTO> getAllTestTemplates() {
        return testTemplateRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TestTemplateResponseDTO convertToDTO(TestTemplate testTemplate) {
        TestTemplateResponseDTO dto = new TestTemplateResponseDTO();
        dto.setTemplateId(testTemplate.getTemplateId());
        dto.setName(testTemplate.getName());
        dto.setDescription(testTemplate.getDescription());
        dto.setParameters(testTemplate.getParameters());
        dto.setBasePrice(testTemplate.getBasePrice());
        dto.setCreatedAt(testTemplate.getCreatedAt());
        return dto;
    }
}
