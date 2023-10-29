package com.psleziona.animedix.service;

import com.psleziona.animedix.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
}
