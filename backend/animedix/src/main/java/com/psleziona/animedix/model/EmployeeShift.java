package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class EmployeeShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployeeShift;
    @NonNull
    private LocalDateTime shiftStart;
    @NonNull
    private LocalDateTime shiftEnd;
    @ManyToMany
    @JoinTable(
            name = "employee_shifts",
            joinColumns = {@JoinColumn(name = "id_employee_shift")},
            inverseJoinColumns = {@JoinColumn(name = "id_employee")}
    )
    private List<Employee> employees;
}
