package com.seek.client_management.service;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientMetricsResponse;
import com.seek.client_management.dto.response.ClientResponse;
import com.seek.client_management.dto.response.ClientWithEstimationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    ClientResponse create(ClientRequest request);

    Page<ClientWithEstimationResponse> getAllClientsWithEstimation(Pageable pageable);

    ClientMetricsResponse getClientMetrics();
}
