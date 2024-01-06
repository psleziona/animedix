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
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idShift;
    @NonNull
    private LocalDateTime shiftStart;
    @NonNull
    private LocalDateTime shiftEnd;
    @ManyToMany
    @JoinTable(
            name = "employee_shift",
            joinColumns = {@JoinColumn(name = "id_shift")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<Employee> employees;
}
