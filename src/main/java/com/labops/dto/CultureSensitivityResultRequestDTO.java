package com.labops.dto;

import lombok.Data;

@Data
public class CultureSensitivityResultRequestDTO {
    private Long antibioticId;
    private String sensitivity;
}
