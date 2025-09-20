package com.labops.service;

import com.labops.dto.DiscountRequestDTO;
import com.labops.dto.InvoiceResponseDTO;

public interface InvoiceService {
    InvoiceResponseDTO requestDiscount(Long invoiceId, DiscountRequestDTO discountRequestDTO);
    InvoiceResponseDTO approveDiscount(Long invoiceId);
    // The return type for downloadReport will be handled later
    Object downloadReport(Long invoiceId);
}
