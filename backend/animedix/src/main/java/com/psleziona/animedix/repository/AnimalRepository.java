package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
