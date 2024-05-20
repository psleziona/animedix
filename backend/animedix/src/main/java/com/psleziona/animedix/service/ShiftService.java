package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Shift;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ShiftService {
    Optional<Shift> getShift(Integer idShift);
    List<Shift> getEmployeeShifts(Integer idEmployee);
    List<Shift> getShiftsByDate(LocalDate date);
    List<Shift> getShiftsByMonth(LocalDate date);
    Shift setShift(Shift shift);
    void deleteShift(Integer idShift);
    void generateEmployeeShifts(Map<String, String> shiftInfo);
    void generateShiftsForMonth(LocalDate date);

}
