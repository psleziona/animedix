package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Role;
import com.psleziona.animedix.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    Page<Employee> getEmployees(Pageable pageable) {
        return employeeService.getEmployees(pageable);
    }

    @GetMapping("/employees/{idEmployee}")
    ResponseEntity<Employee> getEmployee(@PathVariable Integer idEmployee) {
        return ResponseEntity.of(employeeService.getEmployee(idEmployee));
    }

    @PostMapping("/employees")
    ResponseEntity<Void> setEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.setEmployee(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idEmployee}").buildAndExpand(createdEmployee.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/employees/{idEmployee}")
    ResponseEntity<Void> updateEmployee(@RequestBody Employee employee, @PathVariable Integer idEmployee) {
        return employeeService.getEmployee(idEmployee)
                .map(e -> {
                    employeeService.setEmployee(employee);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/employees/{idEmployee}")
    ResponseEntity<Void> deleteEmployee(@PathVariable Integer idEmployee) {
        return employeeService.getEmployee(idEmployee)
                .map(e -> {
                    employeeService.deleteEmployee(idEmployee);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
