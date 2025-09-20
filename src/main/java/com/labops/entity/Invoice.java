package com.labops.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "discount_request")
    private BigDecimal discountRequest;

    @Column(name = "discount_approved")
    private BigDecimal discountApproved;

    @Column(name = "discount_status")
    private String discountStatus;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "payment_mode")
    private String paymentMode;

    private Boolean paid;

    @Column(name = "report_pdf_path")
    private String reportPdfPath;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
