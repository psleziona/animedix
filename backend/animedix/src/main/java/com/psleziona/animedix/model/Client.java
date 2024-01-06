package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@Entity
@NoArgsConstructor(force = true)
public class Client extends User {
    @OneToMany(mappedBy = "owner")
    private List<Animal> animals;
}
