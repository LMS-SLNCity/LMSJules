package com.labops.controller;

import com.labops.dto.VisitRequestDTO;
import com.labops.dto.VisitResponseDTO;
import com.labops.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @PostMapping
    public ResponseEntity<VisitResponseDTO> createVisit(@RequestBody VisitRequestDTO visitRequestDTO) {
        VisitResponseDTO createdVisit = visitService.createVisit(visitRequestDTO);
        return new ResponseEntity<>(createdVisit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitResponseDTO> getVisitById(@PathVariable Long id) {
        VisitResponseDTO visit = visitService.getVisitById(id);
        return ResponseEntity.ok(visit);
    }

    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> getAllVisits(@RequestParam(required = false) String status) {
        List<VisitResponseDTO> visits = visitService.getAllVisits(status);
        return ResponseEntity.ok(visits);
    }
}
