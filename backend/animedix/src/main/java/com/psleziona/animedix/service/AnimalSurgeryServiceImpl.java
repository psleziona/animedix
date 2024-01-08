package com.psleziona.animedix.service;

import com.psleziona.animedix.model.AnimalSurgery;
import com.psleziona.animedix.model.Assortment;
import com.psleziona.animedix.repository.AnimalSurgeryRepository;
import com.psleziona.animedix.repository.AssortmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalSurgeryServiceImpl implements AnimalSurgeryService{
    private final AnimalSurgeryRepository animalSurgeryRepository;
    private final AssortmentRepository assortmentRepository;

    @Override
    public Optional<AnimalSurgery> getSurgery(Integer idSurgery) {
        return animalSurgeryRepository.findById(idSurgery);
    }

    @Override
    public List<AnimalSurgery> getSurgeriesForDay(LocalDateTime date) {
        return animalSurgeryRepository.getAnimalSurgeriesByDate(date);
    }

    @Override
    public Page<AnimalSurgery> getSurgeriesFromPeriod(LocalDateTime start, LocalDateTime finish, Pageable pageable) {
        return animalSurgeryRepository.getAnimalSurgeriesByDateBetween(start, finish, pageable);
    }

    @Override
    public Page<AnimalSurgery> getSurgeriesByDoctor(Integer idEmployee, Pageable pageable) {
        return animalSurgeryRepository.getAnimalSurgeriesByDoctorId(idEmployee, pageable);
    }

    @Override
    public List<AnimalSurgery> getSurgeriesByAnimal(Integer idAnimal) {
        return animalSurgeryRepository.getAnimalSurgeriesByAnimalIdAnimal(idAnimal);
    }

    @Override
    public AnimalSurgery setSurgery(AnimalSurgery animalSurgery) {
        return animalSurgeryRepository.save(animalSurgery);
    }

    @Override
    public void setUsedAssortment(Integer idSurgery, Integer idAssortment) {
        AnimalSurgery animalSurgery = animalSurgeryRepository.findById(idSurgery).orElseThrow();
        Assortment assortment = assortmentRepository.findById(idAssortment).orElseThrow();
        animalSurgery.getUsedAssortment().add(assortment);
    }

    @Override
    public void deleteAnimalSurgery(Integer idAnimalSurgery) {
        animalSurgeryRepository.deleteById(idAnimalSurgery);
    }
}
