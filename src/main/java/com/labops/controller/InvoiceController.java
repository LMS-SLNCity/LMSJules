package com.labops.controller;

import com.labops.dto.DiscountRequestDTO;
import com.labops.dto.InvoiceResponseDTO;
import com.labops.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/{invoiceId}/discount_request")
    public ResponseEntity<InvoiceResponseDTO> requestDiscount(@PathVariable Long invoiceId, @RequestBody DiscountRequestDTO discountRequestDTO) {
        InvoiceResponseDTO invoice = invoiceService.requestDiscount(invoiceId, discountRequestDTO);
        return ResponseEntity.ok(invoice);
    }

    @PatchMapping("/{invoiceId}/approve_discount")
    public ResponseEntity<InvoiceResponseDTO> approveDiscount(@PathVariable Long invoiceId) {
        InvoiceResponseDTO invoice = invoiceService.approveDiscount(invoiceId);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/{invoiceId}/report")
    public ResponseEntity<Object> downloadReport(@PathVariable Long invoiceId) {
        // Implementation will call the service layer to get the PDF
        // For now, returning an empty response
        Object report = invoiceService.downloadReport(invoiceId);
        return ResponseEntity.ok(report);
    }
}
