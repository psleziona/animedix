package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Visit;
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

    @Override
    public Optional<Visit> getVisit(Integer idVisit) {
        return Optional.empty();
    }

    @Override
    public List<Visit> getVisitsForDay(LocalDate date) {
        return null;
    }

    @Override
    public Page<Visit> getVisitsFromPeriod(LocalDate start, LocalDate finish, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Visit> getVisitsByDoctor(Integer idEmployee, Pageable pageable) {
        return null;
    }

    @Override
    public List<Visit> getVisitsByAnimal(Integer idAnimal) {
        return null;
    }

    @Override
    public Visit setVisit(Visit visit, Integer idAnimal) {
        return null;
    }

    @Override
    public void rateVisit(Integer idVisit, Integer rate) {

    }
}
