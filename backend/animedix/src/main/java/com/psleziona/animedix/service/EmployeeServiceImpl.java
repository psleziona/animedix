package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getEmployee(Integer idEmployee) {
        return Optional.empty();
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return null;
    }

    @Override
    public Employee setEmployee(Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(Integer idEmployee) {

    }
}
