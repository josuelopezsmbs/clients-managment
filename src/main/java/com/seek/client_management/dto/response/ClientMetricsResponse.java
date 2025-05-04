package com.seek.client_management.dto.response;

import lombok.Builder;

@Builder
public record ClientMetricsResponse(
        Double averageAge,
        Double standardDeviation
) {
}
