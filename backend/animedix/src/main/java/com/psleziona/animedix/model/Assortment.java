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
    private Double volume;
    private Integer quantity;
    private AssortmentUnit unit;
    private AssortmentType type;
    @NonNull
    private AssortmentCategory category;
    @OneToMany(mappedBy = "assortment")
    @JsonIgnore
    private List<UsedAssortment> usedInSurgeries;

}
