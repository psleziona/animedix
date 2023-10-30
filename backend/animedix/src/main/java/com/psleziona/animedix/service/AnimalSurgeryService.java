package com.psleziona.animedix.service;

import com.psleziona.animedix.model.AnimalSurgery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnimalSurgeryService {
    Optional<AnimalSurgery> getSurgery(Integer idSurgery);
    List<AnimalSurgery> getSurgeriesForDay(LocalDate date);
    Page<AnimalSurgery> getSurgeriesFromPeriod(LocalDate start, LocalDate finish, Pageable pageable);
    Page<AnimalSurgery> getSurgeriesByDoctor(Integer idEmployee, Pageable pageable);
    List<AnimalSurgery> getSurgeriesByAnimal(Integer idAnimal);
    AnimalSurgery setSurgery(AnimalSurgery animalSurgery, Integer idAnimal);
    void setUsedAssortment(Integer idAssortment, Double quantity);

}
