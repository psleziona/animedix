package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Surgery;

import java.util.List;
import java.util.Optional;

public interface SurgeryService {
    Optional<Surgery> getSurgery(Integer idSurgery);
    List<Surgery> getSurgeries();
    Surgery setSurgery(Surgery surgery);
    void deleteSurgery(Integer idSurgery);
}
