package com.psleziona.animedix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class DoctorTerm {
    @JsonIgnoreProperties({"phone","email","role","street","houseNumber","zipcode","city","jobRole","shifts","surgeries","visits"})
    private Employee doctor;
    private List<LocalDateTime> availableTerms;
}
