package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Animal;
import com.psleziona.animedix.model.AnimalSurgery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AnimalSurgeryRepository extends JpaRepository<AnimalSurgery, Integer> {
    List<AnimalSurgery> getAnimalSurgeriesByDate(LocalDateTime date);
    Page<AnimalSurgery> getAnimalSurgeriesByDateBetween(LocalDateTime start, LocalDateTime finish, Pageable pageable);
    Page<AnimalSurgery> getAnimalSurgeriesByIdAnimalSurgery_Doctor_Id(Integer idDoctor, Pageable pageable);
    List<AnimalSurgery> getAnimalSurgeriesByIdAnimalSurgery_Animal_IdAnimal(Integer idAnimal);
}
