package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.Visit;
import com.psleziona.animedix.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class VisitController {
    private final VisitService visitService;

    @GetMapping("/visits/upcoming")
    Page<Visit> getUpcomingVisits(Pageable pageable) {
        return visitService.getUpcomingVisits(pageable);
    }

    @GetMapping("/visits/archive")
    Page<Visit> getArchiveVisit(Pageable pageable) {
        return visitService.getArchiveVisits(pageable);
    }

    @GetMapping("/visits/current")
    ResponseEntity<Visit> getNextVisit() {
        return ResponseEntity.of(visitService.getNextVisit());
    }

    @GetMapping("/visits/{idVisit}")
    ResponseEntity<Visit> getVisit(@PathVariable Integer idVisit) {
        return ResponseEntity.of(visitService.getVisit(idVisit));
    }

    @GetMapping("/visits/day/{date}")
    List<Visit> getVisitsForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return visitService.getVisitsForDay(date);
    }

    @GetMapping("/visits/period/{from}/{to}")
    Page<Visit> getVisitsFromPeriod(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime from,
                                    @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime to,
                                    Pageable pageable) {
        return visitService.getVisitsFromPeriod(from, to, pageable);
    }

    @GetMapping("/visits/doctor/{idDoctor}")
    Page<Visit> getVisitsByDoctor(@PathVariable Integer idDoctor, Pageable pageable) {
        return visitService.getVisitsByDoctor(idDoctor, pageable);
    }

    @GetMapping("/visits/animal/{idAnimal}")
    List<Visit> getAnimalVisits(@PathVariable Integer idAnimal) {
        return visitService.getVisitsByAnimal(idAnimal);
    }

    @PostMapping("/visits/rate/{idVisit}/{rate}")
    ResponseEntity<Void> rateVisit(@PathVariable Integer idVisit, @PathVariable Integer rate) {
        return visitService.getVisit(idVisit)
                .map(v -> {
                    visitService.rateVisit(idVisit, rate);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/visits")
    ResponseEntity<Void> setVisit(@RequestBody Visit visit) {
        Visit createdVisit = visitService.setVisit(visit);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idVisit}").buildAndExpand(createdVisit.getIdVisit()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/visits/{idVisit}")
    ResponseEntity<Void> updateVisit(@PathVariable Integer idVisit, @RequestBody Visit visit) {
        return visitService.getVisit(idVisit)
                .map(v -> {
                    visitService.setVisit(visit);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/visits/{idVisit}")
    ResponseEntity<Void> deleteVisit(@PathVariable Integer idVisit) {
        return visitService.getVisit(idVisit)
                .map(v -> {
                    visitService.deleteVisit(idVisit);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
