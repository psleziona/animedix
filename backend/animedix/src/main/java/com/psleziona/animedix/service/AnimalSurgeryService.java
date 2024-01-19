package com.psleziona.animedix.service;

import com.psleziona.animedix.model.AnimalSurgery;
import com.psleziona.animedix.model.AnimalSurgeryAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AnimalSurgeryService {
    Page<AnimalSurgery> getUpcomingSurgeries(Pageable pageable);
    Page<AnimalSurgery> getArchiveSurgeries(Pageable pageable);
    Optional<AnimalSurgery> getNextSurgery();
    Optional<AnimalSurgery> getSurgery(Integer idSurgery);
    List<AnimalSurgery> getSurgeriesForDay(LocalDateTime date);
    Page<AnimalSurgery> getSurgeriesFromPeriod(LocalDateTime start, LocalDateTime finish, Pageable pageable);
    Page<AnimalSurgery> getSurgeriesByDoctor(Integer idEmployee, Pageable pageable);
    List<AnimalSurgery> getSurgeriesByAnimal(Integer idAnimal);
    void setSurgery(AnimalSurgery animalSurgery);
    AnimalSurgery setSurgery(AnimalSurgeryAppointment animalSurgeryAppointment);
    void setUsedAssortment(Integer idSurgery, Integer idAssortment, Integer quantity, Double volume);
    void deleteAnimalSurgery(Integer idAnimalSurgery);
}
