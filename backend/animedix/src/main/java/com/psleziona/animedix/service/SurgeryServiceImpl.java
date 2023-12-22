package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Surgery;
import com.psleziona.animedix.repository.SurgeryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SurgeryServiceImpl implements SurgeryService{
    private final SurgeryRepository surgeryRepository;

    @Override
    public List<Surgery> getSurgeries() {
        return surgeryRepository.findAll();
    }

    @Override
    public Surgery setSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }

    @Override
    public void deleteSurgery(Integer idSurgery) {
        surgeryRepository.deleteById(idSurgery);
    }
}
