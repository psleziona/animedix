package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Assortment;
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
        return Optional.empty();
    }

    @Override
    public List<Assortment> getAssortmentsToOrder() {
        return null;
    }

    @Override
    public Assortment setAssortment(Assortment assortment) {
        return null;
    }

    @Override
    public void deleteAssortment(Integer idAssortment) {

    }
}
