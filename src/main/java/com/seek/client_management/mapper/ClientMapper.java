package com.seek.client_management.mapper;

import com.seek.client_management.dto.request.ClientRequest;
import com.seek.client_management.dto.response.ClientResponse;
import com.seek.client_management.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponse clientToClientResponse(Client client);

    Client requestToClient(ClientRequest request);
}
