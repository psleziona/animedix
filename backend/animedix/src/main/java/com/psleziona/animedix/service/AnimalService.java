package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AnimalService {
    Optional<Animal> getAnimal(Integer idAnimal);
    Page<Animal> getAnimals(Pageable pageable);
    Animal setAnimal(Animal animal);
    void deleteAnimal(Integer idAnimal);
}
