package com.labops.service;

import com.labops.dto.TestTemplateRequestDTO;
import com.labops.dto.TestTemplateResponseDTO;

import java.util.List;

public interface TestTemplateService {
    TestTemplateResponseDTO createTestTemplate(TestTemplateRequestDTO testTemplateRequestDTO);
    List<TestTemplateResponseDTO> getAllTestTemplates();
}
