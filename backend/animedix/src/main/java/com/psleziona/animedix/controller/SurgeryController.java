package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.Surgery;
import com.psleziona.animedix.service.SurgeryService;
import jakarta.servlet.Servlet;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SurgeryController {
    private final SurgeryService surgeryService;

    @GetMapping("/surgeries")
    List<Surgery> getSurgeries() {
        return surgeryService.getSurgeries();
    }

    @GetMapping("/surgeries/{idSurgery}")
    ResponseEntity<Surgery> getSurgery(@PathVariable Integer idSurgery) {
        return ResponseEntity.of(surgeryService.getSurgery(idSurgery));
    }

    @PostMapping("/surgeries")
    ResponseEntity<Void> setSurgery(@RequestBody Surgery surgery) {
        Surgery createdSurgery = surgeryService.setSurgery(surgery);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idSurgery}").buildAndExpand(createdSurgery.getIdSurgery()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/surgeries/{idSurgery}")
    ResponseEntity<Void> updateSurgery(@RequestBody Surgery surgery, @PathVariable Integer idSurgery) {
        return surgeryService.getSurgery(idSurgery)
                .map(s -> {
                    surgeryService.setSurgery(surgery);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/surgeries/{idSurgery}")
    ResponseEntity<Void> deleteSurgery(@PathVariable Integer idSurgery) {
        return surgeryService.getSurgery(idSurgery)
                .map(s -> {
                    surgeryService.deleteSurgery(idSurgery);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
