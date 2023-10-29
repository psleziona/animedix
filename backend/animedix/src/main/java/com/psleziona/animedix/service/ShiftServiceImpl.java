package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService{
    private final ShiftRepository shiftRepository;
}
