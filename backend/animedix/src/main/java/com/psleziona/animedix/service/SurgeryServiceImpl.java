package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.SurgeryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SurgeryServiceImpl implements SurgeryService{
    private final SurgeryRepository surgeryRepository;
}
