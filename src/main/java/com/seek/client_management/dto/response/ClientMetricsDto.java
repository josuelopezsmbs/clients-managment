package com.seek.client_management.dto.response;

import lombok.Builder;

@Builder
public record ClientMetricsDto(
        Double averageAge,
        Double standardDeviation
) {
}
