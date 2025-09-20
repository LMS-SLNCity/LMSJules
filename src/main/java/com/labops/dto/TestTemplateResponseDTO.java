package com.labops.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class TestTemplateResponseDTO {
    private Long templateId;
    private String name;
    private String description;
    private Map<String, Object> parameters;
    private BigDecimal basePrice;
    private LocalDateTime createdAt;
}
