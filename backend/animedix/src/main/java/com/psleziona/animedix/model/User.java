package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@Entity(name = "users")
@NoArgsConstructor(force = true)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String forename;
    @NonNull
    private String surname;
    @NonNull
    private String phone;
    @NonNull
    private String email;
    @NonNull
    @JsonIgnore
    private String password;
    @NonNull
    private Role role;
}
