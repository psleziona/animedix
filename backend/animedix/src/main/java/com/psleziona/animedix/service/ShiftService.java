package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Shift;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    void generateShifts(Map<String, String> shiftInfo);
}
