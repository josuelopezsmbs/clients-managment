package com.seek.client_management.service.impl;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientMetricsResponse;
import com.seek.client_management.dto.response.ClientResponse;
import com.seek.client_management.dto.response.ClientWithEstimationResponse;
import com.seek.client_management.mapper.ClientMapper;
import com.seek.client_management.model.Client;
import com.seek.client_management.repository.ClientRepository;
import com.seek.client_management.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Value("${app.life-expectancy}")
    private int lifeExpectancyYears;

    @Transactional
    public ClientResponse create(ClientRequest request) {
        var clientEntity = clientMapper.requestToClient(request);

        var clientSaved = clientRepository.saveAndFlush(clientEntity);

        return clientMapper.clientToClientResponse(clientSaved);
    }

    @Override
    public Page<ClientWithEstimationResponse> getAllClientsWithEstimation(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);

        List<ClientWithEstimationResponse> response = clients
                .stream()
                .map(client -> new ClientWithEstimationResponse(client, lifeExpectancyYears)).toList();

        return new PageImpl<>(response, pageable, clients.getTotalElements());
    }

    @Override
    public ClientMetricsResponse getClientMetrics() {
        Object[] result = (Object[]) clientRepository.getClientMetrics();

        Double averageAge = result[0] != null ? ((Number) result[0]).doubleValue() : 0.0;
        Double standardDeviation = result[1] != null ? ((Number) result[1]).doubleValue() : 0.0;

        logger.info("Client metrics -> averageAge: {}, standardDeviation: {}", averageAge, standardDeviation);

        return new ClientMetricsResponse(averageAge, standardDeviation);
    }
}
