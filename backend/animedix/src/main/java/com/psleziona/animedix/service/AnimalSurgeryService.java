package com.psleziona.animedix.service;

import com.psleziona.animedix.model.AnimalSurgery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.client.support.InterceptingHttpAccessor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AnimalSurgeryService {
    Optional<AnimalSurgery> getSurgery(Integer idSurgery);
    List<AnimalSurgery> getSurgeriesForDay(LocalDateTime date);
    Page<AnimalSurgery> getSurgeriesFromPeriod(LocalDateTime start, LocalDateTime finish, Pageable pageable);
    Page<AnimalSurgery> getSurgeriesByDoctor(Integer idEmployee, Pageable pageable);
    List<AnimalSurgery> getSurgeriesByAnimal(Integer idAnimal);
    AnimalSurgery setSurgery(AnimalSurgery animalSurgery);
    void setUsedAssortment(Integer idSurgery, Integer idAssortment);
    void deleteAnimalSurgery(Integer idAnimalSurgery);
}
