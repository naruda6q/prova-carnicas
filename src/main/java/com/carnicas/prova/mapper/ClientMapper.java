package com.carnicas.prova.mapper;

import com.carnicas.prova.dto.ClientDto;
import com.carnicas.prova.entity.Client;

public class ClientMapper {

    public static ClientDto mapToClientDto(Client client){
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getCreatedDate()
        );
    }

    public static Client mapToClient(ClientDto clientDto){
        return new Client(
                clientDto.getId(),
                clientDto.getName(),
                clientDto.getCreatedDate()
        );
    }
}
