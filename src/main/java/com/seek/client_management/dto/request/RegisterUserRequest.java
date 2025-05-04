package com.seek.client_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RegisterUserRequest(
        @NotNull
        @Email
        String email,
        @NotNull
        String password,
        @NotNull
        String role
) {
}
