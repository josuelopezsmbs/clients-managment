package com.seek.client_management.controller;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientDto;
import com.seek.client_management.dto.response.ClientMetricsDto;
import com.seek.client_management.dto.response.ClientWithEstimationDto;
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
    public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientRequest request) {
        return ResponseEntity.ok(clientService.create(request));
    }

    @GetMapping
    public List<ClientWithEstimationDto> getClientsWithEstimation() {
        return clientService.getAllClientsWithEstimation();
    }

    @GetMapping("/metrics")
    public ClientMetricsDto getClientMetrics() {
        return clientService.getClientMetrics();
    }
}
