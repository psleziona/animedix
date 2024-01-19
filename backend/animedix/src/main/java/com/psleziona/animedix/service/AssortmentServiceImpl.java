package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Assortment;
import com.psleziona.animedix.model.AssortmentType;
import com.psleziona.animedix.repository.AssortmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssortmentServiceImpl implements AssortmentService{
    private final AssortmentRepository assortmentRepository;

    @Override
    public Optional<Assortment> getAssortment(Integer idAssortment) {
        return assortmentRepository.findById(idAssortment);
    }

    @Override
    public List<Assortment> getAssortments() {
        return assortmentRepository.findAll();
    }

    @Override
    public List<Assortment> getAssortmentsCritical() {
        return assortmentRepository.findAll()
                .stream().filter(assortment ->
                        (assortment.getType() == AssortmentType.COUNTABLE && assortment.getAlertValue() > assortment.getQuantity())
                        || (assortment.getType() == AssortmentType.UNCOUNTABLE && assortment.getAlertValue() > assortment.getVolume())
                ).toList();
    }

    @Override
    public void fillAssortment(Integer idAssortment, Double quantity) {
        Assortment assortment = assortmentRepository.findById(idAssortment).get();
        if(assortment.getType() == AssortmentType.COUNTABLE) {
            assortment.setQuantity(assortment.getQuantity() + quantity.intValue());
        } else {
            assortment.setVolume(assortment.getVolume() + quantity);
        }
        assortmentRepository.save(assortment);
    }

    @Override
    public List<Assortment> getAssortmentsToOrder() {
        return null;
    }

    @Override
    public Assortment setAssortment(Assortment assortment) {
        return assortmentRepository.save(assortment);
    }

    @Override
    public void deleteAssortment(Integer idAssortment) {
        assortmentRepository.deleteById(idAssortment);
    }
}
