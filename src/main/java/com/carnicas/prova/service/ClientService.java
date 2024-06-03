package com.carnicas.prova.service;

import com.carnicas.prova.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto findByIdClient(Integer id);

    List<ClientDto> findAllClients();

    ClientDto createClient(ClientDto clientDto);

    ClientDto updateClient(Integer clientId, ClientDto clientDto);

    void deleteClient(Integer idClient);
}
