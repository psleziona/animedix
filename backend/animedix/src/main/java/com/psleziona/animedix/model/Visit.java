package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee doctor;

}
