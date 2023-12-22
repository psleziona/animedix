package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Animal;
import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.repository.AnimalRepository;
import com.psleziona.animedix.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final ClientRepository clientRepository;

    @Override
    public Optional<Animal> getAnimal(Integer idAnimal) {
        return animalRepository.findById(idAnimal);
    }

    @Override
    public Page<Animal> getAnimals(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    @Override
    public Animal setAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Integer idAnimal) {
        animalRepository.deleteById(idAnimal);
    }
}
