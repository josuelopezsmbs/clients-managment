package com.seek.client_management.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClientResponse(
        Long id,
        String name,
        String lastname,
        String fullName,
        Short age,
        LocalDate birthDate
) {
}
