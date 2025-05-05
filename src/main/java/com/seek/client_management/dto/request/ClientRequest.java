package com.seek.client_management.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClientRequest(

        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @Min(value = 1)
        Short age,
        @PastOrPresent
        LocalDate birthDate
) {
}
