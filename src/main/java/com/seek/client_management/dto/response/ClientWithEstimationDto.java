package com.seek.client_management.dto.response;

import com.seek.client_management.model.Client;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientWithEstimationDto {
    Long id;
    String name;
    String lastname;
    String fullName;
    Short age;
    LocalDate birthDate;
    LocalDate estimatedDeathDate;

    public ClientWithEstimationDto(Client client, int lifeExpectancyYears) {
        this.id = client.getId();
        this.name = client.getName();
        this.fullName = client.getFullName();
        this.lastname = client.getLastname();
        this.age = client.getAge();
        this.birthDate = client.getBirthDate();
        this.estimatedDeathDate = client.getBirthDate().plusYears(lifeExpectancyYears);
    }
}
