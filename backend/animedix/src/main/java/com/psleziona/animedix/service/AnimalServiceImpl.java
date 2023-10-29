package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
}
