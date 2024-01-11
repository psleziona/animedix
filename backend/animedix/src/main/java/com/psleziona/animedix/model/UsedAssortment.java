package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data
@NoArgsConstructor(force = true)
public class UsedAssortment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsedAssortment;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_assortment")
    private Assortment assortment;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_animal_surgery")
    private AnimalSurgery animalSurgery;

    private Integer quantity;
    private Double amount;

}
