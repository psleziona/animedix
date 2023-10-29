package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.AnimalSurgeryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnimalSurgeryServiceImpl implements AnimalSurgeryService{
    private final AnimalSurgeryRepository animalSurgeryRepository;
}
