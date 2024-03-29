package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSurgery;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    @OneToMany(mappedBy = "surgery")
    @JsonIgnore
    private List<AnimalSurgery> animalSurgeries;
}
