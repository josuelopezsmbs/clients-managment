package com.seek.client_management.service;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientMetricsResponse;
import com.seek.client_management.dto.response.ClientResponse;
import com.seek.client_management.dto.response.ClientWithEstimationResponse;

import java.util.List;

public interface ClientService {
    ClientResponse create(ClientRequest request);

    List<ClientWithEstimationResponse> getAllClientsWithEstimation();

    ClientMetricsResponse getClientMetrics();
}
