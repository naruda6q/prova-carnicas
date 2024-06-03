package com.carnicas.prova.service.impl;

import com.carnicas.prova.dto.ClientDto;
import com.carnicas.prova.mapper.ClientMapper;
import com.carnicas.prova.repository.ClientRepository;
import com.carnicas.prova.entity.Client;
import com.carnicas.prova.exception.ResourceNotFoundException;
import com.carnicas.prova.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        Client savedClient = clientRepository.save(client);

        return ClientMapper.mapToClientDto(savedClient);
    }

    @Override
    public ClientDto updateClient(Integer clientId, ClientDto clientDto) {

        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client is not exists")
        );

        client.setName(clientDto.getName());
        client.setCreatedDate(clientDto.getCreatedDate());

        Client updateClient = clientRepository.save(client);

        return ClientMapper.mapToClientDto(updateClient);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto findByIdClient(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client is not exists"));
        return ClientMapper.mapToClientDto(client);
    }

    @Override
    public List<ClientDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(client -> ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .createdDate(client.getCreatedDate())
                .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteClient(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client is not exists")
        );
        clientRepository.deleteById(id);
    }

}
