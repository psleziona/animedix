package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Page<Animal> getAnimalsByOwnerId(Integer idClient, Pageable pageable);
    List<Animal> getAnimalsByOwnerId(Integer idClient);
}
