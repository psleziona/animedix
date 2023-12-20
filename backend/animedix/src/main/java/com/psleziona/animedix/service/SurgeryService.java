package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Surgery;

import java.util.List;

public interface SurgeryService {
    List<Surgery> getSurgeries();
    Surgery setSurgery(Surgery surgery);
    void deleteSurgery(Integer idSurgery);
}
