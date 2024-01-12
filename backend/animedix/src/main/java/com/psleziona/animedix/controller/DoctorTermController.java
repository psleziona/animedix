package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.DoctorTerm;
import com.psleziona.animedix.service.DoctorTermService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorTermController {
    private final DoctorTermService doctorTermService;

    @GetMapping("/term/{daysFrom}/{daysTo}/{hourFrom}/{hoursTo}")
    List<DoctorTerm> getAvailableVisitTerms(@PathVariable LocalDate daysFrom, @PathVariable LocalDate daysTo, @PathVariable LocalTime hourFrom, @PathVariable LocalTime hoursTo) {
        return doctorTermService.getAvailableTerms(daysFrom,daysTo,hourFrom,hoursTo);
    }

    @GetMapping("/term/doctor/{daysFrom}/{daysTo}")
    ResponseEntity<DoctorTerm> getDoctorAvailableVisitTerms(@PathVariable LocalDate daysFrom, @PathVariable LocalDate daysTo) {
        return ResponseEntity.of(doctorTermService.getDoctorAvailableTerms(daysFrom,daysTo));
    }
}
