package com.psleziona.animedix.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnimalSurgeryAppointment {
    private Integer idAnimal;
    private Integer idDoctor;
    private Integer idSurgery;
    private LocalDateTime date;
}
