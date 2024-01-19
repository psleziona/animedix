package com.psleziona.animedix.controller;


import com.psleziona.animedix.model.AnimalSurgery;
import com.psleziona.animedix.model.AnimalSurgeryAppointment;
import com.psleziona.animedix.model.UsedAssortment;
import com.psleziona.animedix.model.UsedAssortmentRequest;
import com.psleziona.animedix.service.AnimalSurgeryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AnimalSurgeryController {
    private final AnimalSurgeryService animalSurgeryService;

    @GetMapping("/animalSurgeries/upcoming")
    Page<AnimalSurgery> getUpcomingSurgeries(Pageable pageable) {
        return animalSurgeryService.getUpcomingSurgeries(pageable);
    }

    @GetMapping("/animalSurgeries/archive")
    Page<AnimalSurgery> getArchiveSurgeries(Pageable pageable) {
        return animalSurgeryService.getArchiveSurgeries(pageable);
    }

    @GetMapping("/animalSurgeries/current")
    ResponseEntity<AnimalSurgery> getNextSurgery() {
        return ResponseEntity.of(animalSurgeryService.getNextSurgery());
    }

    @GetMapping("/animalSurgeries/{idSurgery}")
    ResponseEntity<AnimalSurgery> getAnimalSurgery(@PathVariable Integer idSurgery) {
        return ResponseEntity.of(animalSurgeryService.getSurgery(idSurgery));
    }

    @GetMapping("/animalSurgeries/day/{date}")
    List<AnimalSurgery> getAnimalSurgeriesInDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return animalSurgeryService.getSurgeriesForDay(date);
    }

    @GetMapping("/animalSurgeries/period/{start}/{finish}")
    Page<AnimalSurgery> getAnimalSurgeriesInDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finish,
                                                Pageable pageable) {
        return animalSurgeryService.getSurgeriesFromPeriod(start, finish, pageable);
    }

    @GetMapping("/animalSurgeries/doctor/{idEmployee}")
    Page<AnimalSurgery> getAnimalSurgeriesByDoctor(@PathVariable Integer idEmployee, Pageable pageable) {
        return animalSurgeryService.getSurgeriesByDoctor(idEmployee, pageable);
    }

    @GetMapping("/animalSurgeries/animal/{idAnimal}")
    List<AnimalSurgery> getAnimalSurgeriesByAnimal(@PathVariable Integer idAnimal) {
        return animalSurgeryService.getSurgeriesByAnimal(idAnimal);
    }

    @PostMapping("/animalSurgeries/assortment")
    ResponseEntity<Void> addAssortmentToSurgery(@RequestBody UsedAssortmentRequest usedAssortment) {
        animalSurgeryService.setUsedAssortment(usedAssortment.getIdSurgery(), usedAssortment.getIdAssortment(), usedAssortment.getQuantity(), usedAssortment.getVolume());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/animalSurgeries")
    ResponseEntity<Void> setAnimalSurgery(@RequestBody AnimalSurgeryAppointment animalSurgery) {
        AnimalSurgery createdAnimalSurgery = animalSurgeryService.setSurgery(animalSurgery);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idSurgery}")
                .buildAndExpand(createdAnimalSurgery.getIdAnimalSurgery()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        headers.add("Access-Control-Expose-Headers","*");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/animalSurgeries/{idSurgery}")
    ResponseEntity<Void> updateAnimalSurgery(@PathVariable Integer idSurgery, @RequestBody AnimalSurgery animalSurgery) {
        return animalSurgeryService.getSurgery(idSurgery)
                .map(s -> {
                    animalSurgeryService.setSurgery(animalSurgery);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/animalSurgieries/{idSurgery}")
    ResponseEntity<Void> deleteAnimalSurgery(@PathVariable Integer idSurgery) {
        return animalSurgeryService.getSurgery(idSurgery)
                .map(s -> {
                    animalSurgeryService.deleteAnimalSurgery(idSurgery);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
