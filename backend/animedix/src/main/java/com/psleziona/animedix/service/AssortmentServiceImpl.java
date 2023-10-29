package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.AssortmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssortmentServiceImpl implements AssortmentService{
    private final AssortmentRepository assortmentRepository;
}
