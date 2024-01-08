package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
public class AnimalSurgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimalSurgery;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    @JsonIgnoreProperties({"owner","visits"})
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties({"shifts","surgeries","visits"})
    private Employee doctor;
    @ManyToOne
    @JoinColumn(name = "id_surgery")
    private Surgery surgery;
    @NonNull
    private LocalDateTime date;
    @ManyToMany
    @JoinTable(
            name = "surgery_assortment",
            joinColumns = {@JoinColumn(name = "id_animal_surgery")},
            inverseJoinColumns = {@JoinColumn(name = "id_surgery_assortment_used")}
    )
    private List<Assortment> usedAssortment;
}
