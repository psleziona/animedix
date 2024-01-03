package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployee(Integer idEmployee);
    Optional<Employee> getEmployeeByEmail(String email);
    Page<Employee> getEmployees(Pageable pageable);
    Employee setEmployee(Employee employee);
    void deleteEmployee(Integer idEmployee);
}
