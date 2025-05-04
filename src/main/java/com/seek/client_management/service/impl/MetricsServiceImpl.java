package com.seek.client_management.service.impl;

import com.seek.client_management.dto.response.ClientMetricsDto;
import com.seek.client_management.repository.ClientRepository;
import com.seek.client_management.service.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricService {

    private static final Logger logger = LoggerFactory.getLogger(MetricsServiceImpl.class);

    private final ClientRepository clientRepository;

    @Override
    public ClientMetricsDto getClientMetrics() {
        Object[] result = (Object[]) clientRepository.getClientMetrics();

        Double averageAge = result[0] != null ? ((Number) result[0]).doubleValue() : 0.0;
        Double standardDeviation = result[1] != null ? ((Number) result[1]).doubleValue() : 0.0;

        log.info("Client metrics -> averageAge: {}, standardDeviation: {}", averageAge, standardDeviation);

        return new ClientMetricsDto(averageAge, standardDeviation);
    }
}
