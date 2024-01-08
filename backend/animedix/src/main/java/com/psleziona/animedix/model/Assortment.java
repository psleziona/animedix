package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
public class Assortment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAssortment;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    @NonNull
    private Double quantity;
    @NonNull
    private AssortmentUnit unit;
    @NonNull
    private AssortmentCategory category;
    @ManyToMany(mappedBy = "usedAssortment")
    @JsonIgnore
    private List<AnimalSurgery> usedInSurgeries;

}
