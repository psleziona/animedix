package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.Assortment;
import com.psleziona.animedix.service.AssortmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AssortmentController {
    private final AssortmentService assortmentService;

    @GetMapping("/assortments/{idAssortment}")
    ResponseEntity<Assortment> getAssortment(@PathVariable Integer idAssortment) {
        return ResponseEntity.of(assortmentService.getAssortment(idAssortment));
    }

    @GetMapping("/assortments")
    List<Assortment> getAssortments() {
        return assortmentService.getAssortments();
    }

    @PostMapping("/assortments")
    ResponseEntity<Void> setAssortment(@RequestBody Assortment assortment) {
        Assortment createdAssortment = assortmentService.setAssortment(assortment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAssortment}")
                .buildAndExpand(createdAssortment.getIdAssortment()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        headers.add("Access-Control-Expose-Headers","*");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/assortments/{idAssortment}")
    ResponseEntity<Void> updateAssortment(@PathVariable Integer idAssortment, @RequestBody Assortment assortment) {
        return assortmentService.getAssortment(idAssortment)
                .map(a -> {
                    assortmentService.setAssortment(assortment);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/assortments/{idAssortment}")
    ResponseEntity<Void> deleteAssortment(@PathVariable Integer idAssortment) {
        return assortmentService.getAssortment(idAssortment)
                .map(a -> {
                    assortmentService.deleteAssortment(idAssortment);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
