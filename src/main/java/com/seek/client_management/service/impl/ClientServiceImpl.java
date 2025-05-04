package com.seek.client_management.service.impl;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientDto;
import com.seek.client_management.dto.response.ClientMetricsDto;
import com.seek.client_management.dto.response.ClientWithEstimationDto;
import com.seek.client_management.mapper.ClientMapper;
import com.seek.client_management.model.Client;
import com.seek.client_management.repository.ClientRepository;
import com.seek.client_management.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Value("${app.life-expectancy}")
    private int lifeExpectancyYears;

    @Transactional
    public ClientDto create(ClientRequest request) {
        var clientEntity = clientMapper.requestToClient(request);

        var clientSaved = clientRepository.saveAndFlush(clientEntity);

        return clientMapper.clientToClientDto(clientSaved);
    }

    @Override
    public List<ClientWithEstimationDto> getAllClientsWithEstimation() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> new ClientWithEstimationDto(client, lifeExpectancyYears))
                .collect(Collectors.toList());
    }

    @Override
    public ClientMetricsDto getClientMetrics() {
        Object[] result = (Object[]) clientRepository.getClientMetrics();

        Double averageAge = result[0] != null ? ((Number) result[0]).doubleValue() : 0.0;
        Double standardDeviation = result[1] != null ? ((Number) result[1]).doubleValue() : 0.0;

        logger.info("Client metrics -> averageAge: {}, standardDeviation: {}", averageAge, standardDeviation);

        return new ClientMetricsDto(averageAge, standardDeviation);
    }
}
