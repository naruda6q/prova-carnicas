package com.carnicas.prova.controller;

import com.carnicas.prova.dto.ClientDto;
import com.carnicas.prova.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private ClientService clientService;

    //createClient
    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
        if((clientDto.getName() == null) || (clientDto.getName().isBlank())){
            return ResponseEntity.badRequest().build();
        }
        ClientDto savedClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(savedClient,HttpStatus.CREATED);
    }

    //findByIdClient
    @GetMapping("{id}")
    public ResponseEntity<ClientDto> findByIdClient(@PathVariable("id") Integer clientId){
        ClientDto clientDto = clientService.findByIdClient(clientId);
        return ResponseEntity.ok(clientDto);
    }

    //findByAll
    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> findAllClients(){
        List<ClientDto> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients);
    }

    //updateClient
    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Integer clientId, @RequestBody ClientDto updatedClientDto){
        ClientDto foundClient = clientService.findByIdClient(clientId);
        if(foundClient.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        ClientDto clientDto = clientService.updateClient(clientId,updatedClientDto);
        return ResponseEntity.ok(clientDto);
    }

    //deleteClient
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer clientId){
        ClientDto clientDto = clientService.findByIdClient(clientId);
        if(clientDto.getId() == null){
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClient(clientId);
        return ResponseEntity.ok("Client deleted succesfully");
    }
}
