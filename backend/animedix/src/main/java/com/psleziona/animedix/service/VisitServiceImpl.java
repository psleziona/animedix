package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Visit;
import com.psleziona.animedix.repository.AnimalRepository;
import com.psleziona.animedix.repository.EmployeeRepository;
import com.psleziona.animedix.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final AnimalRepository animalRepository;

    @Override
    public Optional<Visit> getVisit(Integer idVisit) {
        return visitRepository.findById(idVisit);
    }

    @Override
    public List<Visit> getVisitsForDay(LocalDate date) {
        return visitRepository.findVisitsByDateContains(date);
    }

    @Override
    public Page<Visit> getVisitsFromPeriod(LocalDate start, LocalDate finish, Pageable pageable) {
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
