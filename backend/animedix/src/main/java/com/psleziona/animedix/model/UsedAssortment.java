package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
public class UsedAssortment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsedAssortment;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_assortment")
    @JsonIgnoreProperties({"usedInSurgeries"})
    private Assortment assortment;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_animal_surgery")
    @JsonIgnoreProperties({"animal","doctor","surgery","usedAssortment"})
    private AnimalSurgery animalSurgery;

    private Integer quantity;
    private Double amount;
}
