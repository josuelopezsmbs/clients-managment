package com.seek.client_management.controller;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientMetricsResponse;
import com.seek.client_management.dto.response.ClientResponse;
import com.seek.client_management.dto.response.ClientWithEstimationResponse;
import com.seek.client_management.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/clients")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Client Management", description = "Operations related to client management")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponse.class))),
            @ApiResponse(responseCode = "422", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request) {
        return ResponseEntity.ok(clientService.create(request));
    }

    @Operation(summary = "Get paginated clients with their estimations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of clients with estimations",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientWithEstimationResponse.class)))
    })
    @GetMapping("/with-estimations")
    public Page<ClientWithEstimationResponse> getClientsWithEstimation(Pageable pageable) {
        return clientService.getAllClientsWithEstimation(pageable);
    }

    @Operation(summary = "Get metrics related to clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client metrics",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientMetricsResponse.class)))
    })
    @GetMapping("/metrics")
    public ClientMetricsResponse getClientMetrics() {
        return clientService.getClientMetrics();
    }
}
