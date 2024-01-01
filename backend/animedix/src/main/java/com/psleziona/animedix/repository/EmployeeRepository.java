package com.psleziona.animedix.repository;

import com.psleziona.animedix.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeByEmail(String email);
}
