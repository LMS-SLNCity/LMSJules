package com.labops.service;

import com.labops.dto.InvoiceResponseDTO;

public interface BillingService {
    InvoiceResponseDTO generateBill(Long visitId);
}
