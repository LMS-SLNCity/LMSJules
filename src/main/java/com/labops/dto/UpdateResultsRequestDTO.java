package com.labops.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateResultsRequestDTO {
    private Map<String, Object> results;
}
