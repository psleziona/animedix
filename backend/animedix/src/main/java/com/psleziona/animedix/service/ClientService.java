package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Animal;
import com.psleziona.animedix.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> getClient(Integer idClient);
    Page<Client> getClients(Pageable pageable);
    List<Animal> getClientAnimals(Integer idClient);
    Client setClient(Client client);
    void deleteClient(Integer idClient);
}
