package com.psleziona.animedix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@Entity
@NoArgsConstructor(force = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    @NonNull
    private String forename;
    @NonNull
    private String surname;
    @NonNull
    private String phone;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @OneToMany(mappedBy = "owner")
    private List<Animal> animals;
}
