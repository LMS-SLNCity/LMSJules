package com.labops.controller;

import com.labops.dto.TestTemplateRequestDTO;
import com.labops.dto.TestTemplateResponseDTO;
import com.labops.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-templates")
public class TestTemplateController {

    @Autowired
    private TestTemplateService testTemplateService;

    @PostMapping
    public ResponseEntity<TestTemplateResponseDTO> createTestTemplate(@RequestBody TestTemplateRequestDTO testTemplateRequestDTO) {
        TestTemplateResponseDTO createdTestTemplate = testTemplateService.createTestTemplate(testTemplateRequestDTO);
        return new ResponseEntity<>(createdTestTemplate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TestTemplateResponseDTO>> getAllTestTemplates() {
        List<TestTemplateResponseDTO> testTemplates = testTemplateService.getAllTestTemplates();
        return ResponseEntity.ok(testTemplates);
    }
}
