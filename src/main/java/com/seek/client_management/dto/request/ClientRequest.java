package com.seek.client_management.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClientRequest(

        @NotNull
        String name,
        @NotNull
        String lastname,
        @NotNull
        @Min(value = 1)
        Short age,
        @NotNull
        @PastOrPresent
        LocalDate birthDate
) {
}
