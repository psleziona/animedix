package com.psleziona.animedix.service;

import com.psleziona.animedix.auth.AuthService;
import com.psleziona.animedix.model.*;
import com.psleziona.animedix.repository.AnimalSurgeryRepository;
import com.psleziona.animedix.repository.AssortmentRepository;
import com.psleziona.animedix.repository.UsedAssortmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalSurgeryServiceImpl implements AnimalSurgeryService{
    private final AnimalSurgeryRepository animalSurgeryRepository;
    private final AssortmentRepository assortmentRepository;
    private final UsedAssortmentRepository usedAssortmentRepository;
    private final AuthService authService;
    private final ClientService clientService;

    @Override
    public Page<AnimalSurgery> getUpcomingSurgeries(Pageable pageable) {
        User currentUser = authService.getSessionUser();
        List<AnimalSurgery> animalSurgeries;
        if(currentUser.getRole() == Role.CLIENT) {
            animalSurgeries = clientService.getClientAnimals(currentUser.getId())
                    .stream()
                    .map(Animal::getSurgeries)
                    .flatMap(Collection::stream)
                    .filter(s -> s.getDate().isAfter(LocalDateTime.now())).toList();
        } else
            animalSurgeries = ((Employee) currentUser).getSurgeries()
                    .stream()
                    .filter(s -> s.getDate().isAfter(LocalDateTime.now())).toList();
        return new PageImpl<>(animalSurgeries);
    }

    @Override
    public Page<AnimalSurgery> getArchiveSurgeries(Pageable pageable) {
        User currentUser = authService.getSessionUser();
        List<AnimalSurgery> animalSurgeries;
        if(currentUser.getRole() == Role.CLIENT) {
            animalSurgeries = clientService.getClientAnimals(currentUser.getId())
                    .stream()
                    .map(Animal::getSurgeries)
                    .flatMap(Collection::stream)
                    .filter(s -> s.getDate().isBefore(LocalDateTime.now())).toList();
        } else
            animalSurgeries = ((Employee) currentUser).getSurgeries()
                    .stream()
                    .filter(s -> s.getDate().isBefore(LocalDateTime.now())).toList();
        return new PageImpl<>(animalSurgeries);
    }

    @Override
    public Optional<AnimalSurgery> getNextSurgery() {
        User currentUser = authService.getSessionUser();
        if(currentUser.getRole() == Role.CLIENT) {
            return clientService.getClientAnimals(currentUser.getId())
                    .stream()
                    .map(Animal::getSurgeries)
                    .flatMap(Collection::stream)
                    .filter(s -> s.getDate().plusMinutes(30).isAfter(LocalDateTime.now()))
                    .findFirst();
        }
        return ((Employee) currentUser).getSurgeries()
                .stream()
                .filter(s -> s.getDate().plusMinutes(30).isAfter(LocalDateTime.now())).findFirst();
    }

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
    public void setUsedAssortment(Integer idSurgery, Integer idAssortment, Integer quantity, Double volume) {
        AnimalSurgery animalSurgery = animalSurgeryRepository.findById(idSurgery).orElseThrow();
        Assortment assortment = assortmentRepository.findById(idAssortment).orElseThrow();
        UsedAssortment usedAssortment;
        if(assortment.getType() == AssortmentType.COUNTABLE) {
            assortment.setQuantity(assortment.getQuantity() - quantity);
            usedAssortment = UsedAssortment
                    .builder()
                    .quantity(quantity)
                    .animalSurgery(animalSurgery)
                    .assortment(assortment)
                    .build();
        }
        else {
            assortment.setVolume(assortment.getVolume() - volume);
            usedAssortment = UsedAssortment
                    .builder()
                    .amount(volume)
                    .animalSurgery(animalSurgery)
                    .assortment(assortment)
                    .build();
        }
        UsedAssortment created = usedAssortmentRepository.save(usedAssortment);
        animalSurgery.getUsedAssortment().add(created);
        assortmentRepository.save(assortment);
    }

    @Override
    public void deleteAnimalSurgery(Integer idAnimalSurgery) {
        animalSurgeryRepository.deleteById(idAnimalSurgery);
    }
}
