package com.labops.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InvoiceResponseDTO {
    private Long invoiceId;
    private Long visitId;
    private BigDecimal totalAmount;
    private BigDecimal discountRequest;
    private BigDecimal discountApproved;
    private String discountStatus;
    private String approvedBy;
    private String paymentMode;
    private Boolean paid;
    private String reportPdfPath;
    private LocalDateTime createdAt;
}
