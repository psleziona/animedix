package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Assortment;
import com.psleziona.animedix.model.UsedAssortment;

import java.util.List;
import java.util.Optional;

public interface AssortmentService {
    Optional<Assortment> getAssortment(Integer idAssortment);
    List<Assortment> getAssortments();
    List<Assortment> getAssortmentsCritical();
    List<Assortment> getAssortmentsToOrder();
    Assortment setAssortment(Assortment assortment);
    void deleteAssortment(Integer idAssortment);
    void fillAssortment(Integer idAssortment, Double quantity);
}
