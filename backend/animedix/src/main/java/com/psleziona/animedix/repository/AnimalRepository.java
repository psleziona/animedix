package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Page<Animal> getAnimalsByOwnerId(Integer idClient, Pageable pageable);
}
