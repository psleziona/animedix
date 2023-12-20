package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;

    @Override
    public Optional<Client> getClient(Integer idClient) {
        return Optional.empty();
    }

    @Override
    public Page<Client> getClients(Pageable pageable) {
        return null;
    }

    @Override
    public Client setClient(Client client) {
        return null;
    }

    @Override
    public void deleteClient(Integer idClient) {

    }
}
