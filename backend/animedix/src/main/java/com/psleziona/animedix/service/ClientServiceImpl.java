package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Animal;
import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;

    @Override
    public Optional<Client> getClient(Integer idClient) {
        return clientRepository.findById(idClient);
    }

    @Override
    public Page<Client> getClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public List<Animal> getClientAnimals(Integer idClient) {
        Client client = clientRepository.findById(idClient).get();
        return client.getAnimals();
    }

    @Override
    public Client setClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Integer idClient) {
        clientRepository.deleteById(idClient);
    }
}
