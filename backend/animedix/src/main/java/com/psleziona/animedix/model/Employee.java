package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployee;
    @NonNull
    private String forename;
    @NonNull
    private String surname;
    @NonNull
    private String phone;
    @NonNull
    private String email;
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
    @OneToMany(mappedBy = "idAnimalSurgery.doctor")
    private List<AnimalSurgery> surgeries;
    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits;
}
