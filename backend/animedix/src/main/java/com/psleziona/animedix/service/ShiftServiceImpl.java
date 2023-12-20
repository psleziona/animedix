package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Shift;
import com.psleziona.animedix.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService{
    private final ShiftRepository shiftRepository;

    @Override
    public Optional<Shift> getShift(Integer idShift) {
        return Optional.empty();
    }

    @Override
    public List<Shift> getEmployeeShifts(Integer idEmployee) {
        return null;
    }

    @Override
    public List<Shift> getShiftsByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public Shift setShift(Shift shift) {
        return null;
    }

    @Override
    public void deleteShift(Integer idShift) {

    }
}
