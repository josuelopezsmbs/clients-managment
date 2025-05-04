package com.seek.client_management.controller;

import com.seek.client_management.dto.response.ClientMetricsDto;
import com.seek.client_management.service.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients-metrics")
@Slf4j
@RequiredArgsConstructor
public class MetricController {

    private final MetricService metricService;

    @GetMapping
    public ClientMetricsDto getClientMetrics() {
        return metricService.getClientMetrics();
    }
}
