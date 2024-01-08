package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
public class Employee extends User {
    @NonNull
    private String street;
    @NonNull
    private Integer houseNumber;
    @NonNull
    private String zipcode;
    @NonNull
    private String city;
    @NonNull
    private JobRole jobRole;
    @ManyToMany(mappedBy = "employees")
    private List<Shift> shifts;
    @OneToMany(mappedBy = "doctor")
    private List<AnimalSurgery> surgeries;
    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits;
}
