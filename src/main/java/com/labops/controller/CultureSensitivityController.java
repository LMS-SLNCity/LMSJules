package com.labops.controller;

import com.labops.dto.CultureSensitivityResultRequestDTO;
import com.labops.dto.CultureSensitivityResultResponseDTO;
import com.labops.service.CultureSensitivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits/{visitId}/tests/{testId}/c_s_results")
public class CultureSensitivityController {

    @Autowired
    private CultureSensitivityService cultureSensitivityService;

    @PostMapping
    public ResponseEntity<CultureSensitivityResultResponseDTO> addOrUpdateCultureSensitivityResult(
            @PathVariable Long visitId,
            @PathVariable Long testId,
            @RequestBody CultureSensitivityResultRequestDTO requestDTO) {
        CultureSensitivityResultResponseDTO result = cultureSensitivityService.addOrUpdateCultureSensitivityResult(visitId, testId, requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CultureSensitivityResultResponseDTO>> getCultureSensitivityResults(
            @PathVariable Long visitId,
            @PathVariable Long testId) {
        List<CultureSensitivityResultResponseDTO> results = cultureSensitivityService.getCultureSensitivityResults(visitId, testId);
        return ResponseEntity.ok(results);
    }
}
