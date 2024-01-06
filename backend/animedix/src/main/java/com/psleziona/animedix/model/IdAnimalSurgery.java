package com.psleziona.animedix.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Embeddable
public class IdAnimalSurgery implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "id_surgery")
    private Surgery surgery;

    @ManyToOne
    @JoinColumn(name = "id")
    private Employee doctor;
}
