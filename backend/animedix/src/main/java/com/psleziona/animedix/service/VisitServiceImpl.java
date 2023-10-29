package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
}
