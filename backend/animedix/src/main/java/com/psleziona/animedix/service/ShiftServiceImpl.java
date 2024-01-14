package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Shift;
import com.psleziona.animedix.repository.EmployeeRepository;
import com.psleziona.animedix.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService{
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Shift> getShift(Integer idShift) {
        return shiftRepository.findById(idShift);
    }

    @Override
    public List<Shift> getEmployeeShifts(Integer idEmployee) {
        Employee employee = employeeRepository.findById(idEmployee).orElseThrow();
        return employee.getShifts();
    }

    @Override
    public List<Shift> getShiftsByDate(LocalDate date) {
        return shiftRepository.findAll()
                .stream()
                .filter(s -> s.getShiftStart().toLocalDate().equals(date)).toList();
    }

    @Override
    public List<Shift> getShiftsByMonth(LocalDate date) {
        return shiftRepository.findAll()
                .stream()
                .filter(s -> s.getShiftStart().getMonth() == date.getMonth())
                .sorted((o1, o2) -> o1.getShiftStart().isBefore(o2.getShiftStart()) ? -1 : 1).toList();
    }

    @Override
    public Shift setShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public void deleteShift(Integer idShift) {
        shiftRepository.deleteById(idShift);
    }
}
