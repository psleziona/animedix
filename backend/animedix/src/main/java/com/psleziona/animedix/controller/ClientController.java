package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.Animal;
import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.model.Visit;
import com.psleziona.animedix.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    Page<Client> getClients(Pageable pageable) {
        return clientService.getClients(pageable);
    }

    @GetMapping("/clients/{idClient}")
    ResponseEntity<Client> getClient(@PathVariable Integer idClient) {
        return ResponseEntity.of(clientService.getClient(idClient));
    }

    @GetMapping("/clients/{idClient}/visits")
    List<Visit> getClientVisits(@PathVariable Integer idClient) {
        return clientService.getClientVisits(idClient);
    }

    @GetMapping("/clients/{idClient}/animals")
    List<Animal> getClientAnimals(@PathVariable Integer idClient) {
        return clientService.getClientAnimals(idClient);
    }

    @PutMapping("/clients/{idClient}")
    ResponseEntity<Void> editClient(@PathVariable Integer idClient, @RequestBody Client client) {
        return clientService.getClient(idClient)
                .map(c -> {
                    clientService.setClient(client);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/clients/{idClient}")
    ResponseEntity<Void> deleteClient(@PathVariable Integer idClient) {
        return clientService.getClient(idClient)
                .map(c -> {
                    clientService.deleteClient(idClient);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
