package com.psleziona.animedix.model;

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
    @EmbeddedId
    private IdAnimalSurgery idAnimalSurgery;
    @NonNull
    private LocalDateTime date;
    @ManyToMany
    @JoinTable(
            name = "surgery_assortment",
            joinColumns = {@JoinColumn(name = "id_animal"),@JoinColumn(name = "id_surgery"), @JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_surgery_assortment_used")}
    )
    private List<Assortment> usedAssortment;
}
