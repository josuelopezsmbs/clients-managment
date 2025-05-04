package com.seek.client_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginUserRequest(
        @NotNull
        @Email
        String email,
        @NotNull
        String password
) {
}
