package com.seek.client_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterUserRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String role
) {
}
