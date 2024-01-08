package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"owner"})
    private List<Animal> animals;
}
