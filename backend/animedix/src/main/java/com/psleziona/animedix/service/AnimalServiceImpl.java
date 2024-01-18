package com.psleziona.animedix.service;

import com.psleziona.animedix.auth.AuthService;
import com.psleziona.animedix.model.Animal;
import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.model.Role;
import com.psleziona.animedix.model.User;
import com.psleziona.animedix.repository.AnimalRepository;
import com.psleziona.animedix.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final AuthService authService;

    @Override
    public Optional<Animal> getAnimal(Integer idAnimal) {
        User currentUser = authService.getSessionUser();
        Animal animal = animalRepository.findById(idAnimal).orElseThrow();
        if(currentUser.getRole() == Role.DOCTOR || currentUser.getRole() == Role.ADMIN || (currentUser.getRole() == Role.CLIENT && ((Client)currentUser).getAnimals().contains(animal)))
            return animalRepository.findById(idAnimal);
        return Optional.of(null);
    }

    @Override
    public Page<Animal> getAnimals(Pageable pageable) {
        User currentUser = authService.getSessionUser();
        if(currentUser.getRole() == Role.CLIENT) {
            return animalRepository.getAnimalsByOwnerId(currentUser.getId(), pageable);
        }
        return animalRepository.findAll(pageable);
    }

    @Override
    public Animal setAnimal(Animal animal) {
        Client user = (Client) authService.getSessionUser();
        animal.setOwner(user);
        return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Integer idAnimal) {
        animalRepository.deleteById(idAnimal);
    }
}
