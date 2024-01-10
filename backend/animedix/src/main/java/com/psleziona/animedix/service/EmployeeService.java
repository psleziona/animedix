package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Employee;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployee(Integer idEmployee);
    Optional<Employee> getEmployeeByEmail(String email);
    Page<Employee> getEmployees(Pageable pageable);
    Employee setEmployee(Employee employee);
    void deleteEmployee(Integer idEmployee);
    List<Employee> getEmployees();
}
