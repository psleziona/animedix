package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(force = true)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;
    @NonNull
    private String species;
    private String breed;
    @NonNull
    private String name;
    @NonNull
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties("animals")
    private Client owner;
    @OneToMany(mappedBy = "animal")
    @JsonIgnoreProperties({"animal", "doctor"})
    private List<Visit> visits;
    @OneToMany(mappedBy = "animal")
    @JsonIgnoreProperties({"doctor","surgery","usedAssortment","animal"})
    private List<AnimalSurgery> surgeries;

}
