package com.seek.client_management.service;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientDto;
import com.seek.client_management.dto.response.ClientWithEstimationDto;

import java.util.List;

public interface ClientService {
    ClientDto create(ClientRequest request);

    List<ClientWithEstimationDto> getAllClientsWithEstimation();
}
