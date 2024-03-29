package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    List<Visit> findVisitsByDateContains(LocalDate date);
    Page<Visit> findVisitsByDateBetween(LocalDateTime date, LocalDateTime date2, Pageable pageable);
    Page<Visit> findVisitsByDoctor_Id(Integer idEmployee, Pageable pageable);
    List<Visit> findVisitsByAnimal_IdAnimal(Integer idAnimal);
}
