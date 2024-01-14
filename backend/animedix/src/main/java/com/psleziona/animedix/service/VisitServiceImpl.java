package com.psleziona.animedix.service;

import com.psleziona.animedix.auth.AuthService;
import com.psleziona.animedix.model.*;
import com.psleziona.animedix.repository.AnimalRepository;
import com.psleziona.animedix.repository.EmployeeRepository;
import com.psleziona.animedix.repository.VisitRepository;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final AnimalRepository animalRepository;
    private final AuthService authService;

    @Override
    public Page<Visit> getUpcomingVisits(Pageable pageable) {
        User currentUser = authService.getSessionUser();
        if(currentUser.getRole() == Role.CLIENT) {
            var visits = animalRepository.getAnimalsByOwnerId(currentUser.getId())
                    .stream().map(Animal::getVisits)
                    .flatMap(v -> v.stream().filter(visit -> visit.getDate().isAfter(LocalDateTime.now())))
                    .toList();
            return new PageImpl<>(visits);
            }
        var visits = ((Employee)currentUser).getVisits()
                .stream()
                .filter(visit -> visit.getDate().isAfter(LocalDateTime.now()))
                .toList();
        return new PageImpl<>(visits);
    }

    @Override
    public Page<Visit> getArchiveVisits(Pageable pageable) {
        User currentUser = authService.getSessionUser();
        if(currentUser.getRole() == Role.CLIENT) {
            var visits = animalRepository.getAnimalsByOwnerId(currentUser.getId())
                    .stream().map(Animal::getVisits)
                    .flatMap(v -> v.stream().filter(visit -> visit.getDate().isBefore(LocalDateTime.now())))
                    .toList();
            return new PageImpl<>(visits);
        }
        var visits = ((Employee)currentUser).getVisits()
                .stream()
                .filter(visit -> visit.getDate().isBefore(LocalDateTime.now()))
                .toList();
        return new PageImpl<>(visits);
    }

    @Override
    public Optional<Visit> getNextVisit() {
        User currentUser = authService.getSessionUser();
        //wybiera nie najszybsza
        if(currentUser.getRole() == Role.CLIENT) {
            return animalRepository.getAnimalsByOwnerId(currentUser.getId())
                    .stream()
                    .map(Animal::getVisits)
                    .flatMap(Collection::stream)
                    .filter(v -> v.getDate().plusMinutes(30).isAfter(LocalDateTime.now()))
                    .sorted()
                    .findFirst();
        }
        return ((Employee)currentUser).getVisits()
                .stream()
                .filter(visit -> visit.getDate().plusMinutes(30).isAfter(LocalDateTime.now()))
                .findFirst();
    }

    @Override
    public Optional<Visit> getVisit(Integer idVisit) {
        return visitRepository.findById(idVisit);
    }

    @Override
    public List<Visit> getVisitsForDay(LocalDate date) {
        return visitRepository.findVisitsByDateContains(date);
    }

    @Override
    public Page<Visit> getVisitsFromPeriod(LocalDateTime start, LocalDateTime finish, Pageable pageable) {
        return visitRepository.findVisitsByDateBetween(start, finish, pageable);
    }

    @Override
    public Page<Visit> getVisitsByDoctor(Integer idEmployee, Pageable pageable) {
        return visitRepository.findVisitsByDoctor_Id(idEmployee, pageable);
    }

    @Override
    public List<Visit> getVisitsByAnimal(Integer idAnimal) {
        return visitRepository.findVisitsByAnimal_IdAnimal(idAnimal);
    }

    @Override
    public Visit setVisit(Visit visit) {
       return visitRepository.save(visit);
    }

    @Override
    public void rateVisit(Integer idVisit, Integer rate) {
        Visit visit = visitRepository.findById(idVisit).get();
        visit.setDoctorRate(rate);
        visitRepository.save(visit);
    }

    @Override
    public void deleteVisit(Integer idVisit) {
        visitRepository.deleteById(idVisit);
    }
}
