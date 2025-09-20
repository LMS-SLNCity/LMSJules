package com.labops.dto;

import lombok.Data;

@Data
public class CultureSensitivityResultResponseDTO {
    private Long id;
    private Long visitId;
    private Long testId;
    private Long antibioticId;
    private String sensitivity;
}
