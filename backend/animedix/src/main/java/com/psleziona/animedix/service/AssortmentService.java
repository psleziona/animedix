package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Assortment;

import java.util.List;
import java.util.Optional;

public interface AssortmentService {
    Optional<Assortment> getAssortment(Integer idAssortment);
    List<Assortment> getAssortments();
    List<Assortment> getAssortmentsToOrder();
    Assortment setAssortment(Assortment assortment);
    void deleteAssortment(Integer idAssortment);
}
