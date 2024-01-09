package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitService {
    Page<Visit> getUpcomingVisits(Pageable pageable);
    Optional<Visit> getVisit(Integer idVisit);
    List<Visit> getVisitsForDay(LocalDate date);
    Page<Visit> getVisitsFromPeriod(LocalDateTime start, LocalDateTime finish, Pageable pageable);
    Page<Visit> getVisitsByDoctor(Integer idEmployee, Pageable pageable);
    List<Visit> getVisitsByAnimal(Integer idAnimal);
    Visit setVisit(Visit visit);
    void rateVisit(Integer idVisit, Integer rate);
    void deleteVisit(Integer idVisit);
}
