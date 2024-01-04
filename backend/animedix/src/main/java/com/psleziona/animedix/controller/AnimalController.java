package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.Animal;
import com.psleziona.animedix.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/animals/{idAnimal}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Integer idAnimal) {
        return ResponseEntity.of(animalService.getAnimal(idAnimal));
    }

    @GetMapping("/animals")
    public Page<Animal> getAnimals(Pageable pageable) {
        return animalService.getAnimals(pageable);
    }

    @PostMapping("/animals")
    public ResponseEntity<Void> saveAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.setAnimal(animal);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idAnimal}").buildAndExpand(createdAnimal.getIdAnimal()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        headers.add("Access-Control-Expose-Headers","*");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/animals/{IdAnimal}")
    public ResponseEntity<Void> updateAnimal(@RequestBody Animal animal, @PathVariable Integer idAnimal) {
        return animalService.getAnimal(idAnimal)
                .map(a -> {
                    animalService.setAnimal(animal);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/animals/{idAnimal}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Integer idAnimal) {
        return animalService.getAnimal(idAnimal)
                .map(a -> {
                    animalService.deleteAnimal(idAnimal);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
