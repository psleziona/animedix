package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(force = true)
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVisit;
    @NonNull
    private LocalDateTime date;
    private String ownerComments;
    @NonNull
    private String doctorComments;
    private Integer doctorRate;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    @JsonIgnoreProperties({"owner","visits"})
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties({"shifts", "surgeries", "visits"})
    private Employee doctor;

}
