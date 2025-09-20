package com.labops.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscountRequestDTO {
    private BigDecimal discountAmount;
}
