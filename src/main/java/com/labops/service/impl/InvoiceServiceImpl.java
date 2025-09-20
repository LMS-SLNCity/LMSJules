package com.labops.service.impl;

import com.labops.dto.DiscountRequestDTO;
import com.labops.dto.InvoiceResponseDTO;
import com.labops.entity.Invoice;
import com.labops.repository.InvoiceRepository;
import com.labops.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public InvoiceResponseDTO requestDiscount(Long invoiceId, DiscountRequestDTO discountRequestDTO) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setDiscountRequest(discountRequestDTO.getDiscountAmount());
        invoice.setDiscountStatus("Requested");
        invoice = invoiceRepository.save(invoice);
        return convertToDTO(invoice);
    }

    @Override
    public InvoiceResponseDTO approveDiscount(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setDiscountApproved(invoice.getDiscountRequest());
        invoice.setDiscountStatus("Approved");
        // In a real application, the approvedBy would be set from the security context
        invoice.setApprovedBy("admin"); // Placeholder
        invoice = invoiceRepository.save(invoice);
        return convertToDTO(invoice);
    }

    @Override
    public Object downloadReport(Long invoiceId) {
        // PDF generation logic will be implemented later
        return null;
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
