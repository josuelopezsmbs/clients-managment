package com.seek.client_management.dto.response;

import lombok.Builder;

@Builder
public record LoginUserResponse(
        String token
) {
}
