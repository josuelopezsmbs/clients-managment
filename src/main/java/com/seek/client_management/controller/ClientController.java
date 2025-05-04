package com.seek.client_management.controller;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientMetricsResponse;
import com.seek.client_management.dto.response.ClientResponse;
import com.seek.client_management.dto.response.ClientWithEstimationResponse;
import com.seek.client_management.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    public final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody @Valid ClientRequest request) {
        return ResponseEntity.ok(clientService.create(request));
    }

    @GetMapping
    public List<ClientWithEstimationResponse> getClientsWithEstimation() {
        return clientService.getAllClientsWithEstimation();
    }

    @GetMapping("/metrics")
    public ClientMetricsResponse getClientMetrics() {
        return clientService.getClientMetrics();
    }
}
