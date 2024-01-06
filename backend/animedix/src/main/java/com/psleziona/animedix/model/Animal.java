package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

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
    private LocalDateTime dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "id")
    private Client owner;
    @OneToMany(mappedBy = "animal")
    private List<Visit> visits;
    @OneToMany(mappedBy = "idAnimalSurgery.animal")
    private List<AnimalSurgery> surgeries;

}
