package com.labops.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class TestTemplateRequestDTO {
    private String name;
    private String description;
    private Map<String, Object> parameters;
    private BigDecimal basePrice;
}
