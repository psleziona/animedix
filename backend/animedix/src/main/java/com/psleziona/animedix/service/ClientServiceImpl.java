package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
}
