package com.labops.controller;

import com.labops.dto.LabTestRequestDTO;
import com.labops.dto.LabTestResponseDTO;
import com.labops.dto.UpdateResultsRequestDTO;
import com.labops.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visits/{visitId}/tests")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @PostMapping
    public ResponseEntity<LabTestResponseDTO> addTestToVisit(@PathVariable Long visitId, @RequestBody LabTestRequestDTO labTestRequestDTO) {
        LabTestResponseDTO createdLabTest = labTestService.addTestToVisit(visitId, labTestRequestDTO);
        return new ResponseEntity<>(createdLabTest, HttpStatus.CREATED);
    }

    @PatchMapping("/{testId}/results")
    public ResponseEntity<LabTestResponseDTO> updateResults(@PathVariable Long visitId, @PathVariable Long testId, @RequestBody UpdateResultsRequestDTO updateResultsRequestDTO) {
        LabTestResponseDTO updatedLabTest = labTestService.updateResults(visitId, testId, updateResultsRequestDTO);
        return ResponseEntity.ok(updatedLabTest);
    }

    @PatchMapping("/{testId}/approve")
    public ResponseEntity<LabTestResponseDTO> approveResults(@PathVariable Long visitId, @PathVariable Long testId) {
        LabTestResponseDTO approvedLabTest = labTestService.approveResults(visitId, testId);
        return ResponseEntity.ok(approvedLabTest);
    }
}
