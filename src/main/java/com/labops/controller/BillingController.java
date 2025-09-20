package com.labops.controller;

import com.labops.dto.InvoiceResponseDTO;
import com.labops.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visits/{visitId}/bill")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public ResponseEntity<InvoiceResponseDTO> generateBill(@PathVariable Long visitId) {
        InvoiceResponseDTO invoice = billingService.generateBill(visitId);
        return ResponseEntity.ok(invoice);
    }
}
