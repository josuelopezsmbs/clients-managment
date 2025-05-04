package com.seek.client_management.service.impl;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientDto;
import com.seek.client_management.dto.response.ClientWithEstimationDto;
import com.seek.client_management.mapper.ClientMapper;
import com.seek.client_management.model.Client;
import com.seek.client_management.repository.ClientRepository;
import com.seek.client_management.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

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
}
