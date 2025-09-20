package com.labops.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class LabTestResponseDTO {
    private Long testId;
    private Long visitId;
    private Long testTemplateId;
    private String status;
    private BigDecimal price;
    private Map<String, Object> results;
    private Boolean approved;
    private String approvedBy;
    private LocalDateTime approvedAt;
}
