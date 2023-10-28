package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
