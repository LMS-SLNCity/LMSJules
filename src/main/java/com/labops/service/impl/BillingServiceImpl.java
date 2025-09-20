package com.labops.service.impl;

import com.labops.dto.InvoiceResponseDTO;
import com.labops.entity.Invoice;
import com.labops.entity.LabTest;
import com.labops.entity.Visit;
import com.labops.repository.InvoiceRepository;
import com.labops.repository.LabTestRepository;
import com.labops.repository.VisitRepository;
import com.labops.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private LabTestRepository labTestRepository;

    @Override
    public InvoiceResponseDTO generateBill(Long visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        List<LabTest> labTests = labTestRepository.findAllByVisit(visit);
        BigDecimal totalAmount = labTests.stream()
                .map(LabTest::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Invoice invoice = new Invoice();
        invoice.setVisit(visit);
        invoice.setTotalAmount(totalAmount);
        invoice.setDiscountStatus("None");
        invoice.setPaid(false);

        invoice = invoiceRepository.save(invoice);

        return convertToDTO(invoice);
    }

    private InvoiceResponseDTO convertToDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setVisitId(invoice.getVisit().getVisitId());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setDiscountRequest(invoice.getDiscountRequest());
        dto.setDiscountApproved(invoice.getDiscountApproved());
        dto.setDiscountStatus(invoice.getDiscountStatus());
        dto.setApprovedBy(invoice.getApprovedBy());
        dto.setPaymentMode(invoice.getPaymentMode());
        dto.setPaid(invoice.getPaid());
        dto.setReportPdfPath(invoice.getReportPdfPath());
        dto.setCreatedAt(invoice.getCreatedAt());
        return dto;
    }
}
