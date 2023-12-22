package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {
//    @Query(value = "SELECT s FROM Shift s WHERE s.shiftStart>= :date AND s.shiftEnd <= :date")
//    List<Shift> getShiftsByDate(LocalDateTime date);
    List<Shift> findShiftsByShiftStartContainsAndShiftEndContains(LocalDate start, LocalDate end);
}
