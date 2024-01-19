package com.psleziona.animedix.controller;

import com.psleziona.animedix.model.Shift;
import com.psleziona.animedix.service.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ShiftController {
    private final ShiftService shiftService;
    @GetMapping("/shifts/{idShift}")
    ResponseEntity<Shift> getShift(@PathVariable Integer idShift) {
        return ResponseEntity.of(shiftService.getShift(idShift));
    }

    @GetMapping("/shifts/employee/{idEmployee}")
    List<Shift> getEmployeeShifts(@PathVariable Integer idEmployee) {
        return shiftService.getEmployeeShifts(idEmployee);
    }

    @GetMapping("/shifts/date/{date}")
    List<Shift> getShiftsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
        return shiftService.getShiftsByDate(date);
    }

    @GetMapping("/shifts/month/{date}")
    List<Shift> getShiftsByMonth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM") LocalDate date) {
        return shiftService.getShiftsByMonth(date);
    }

    @PostMapping("/shifts/generate")
    ResponseEntity<Void> generateShifts(@RequestBody Map<String, String> shiftInfos) {
        shiftService.generateShifts(shiftInfos);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/shifts")
    ResponseEntity<Void> setShift(@RequestBody Shift shift) {
        Shift createdShift = shiftService.setShift(shift);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idShift}").buildAndExpand(createdShift.getIdShift()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/shifts/{idShift}")
    ResponseEntity<Void> updateShift(@PathVariable Integer idShift, @RequestBody Shift shift) {
        return shiftService.getShift(idShift)
                .map(s -> {
                    shiftService.setShift(shift);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/shifts/{idShift}")
    ResponseEntity<Void> deleteShift(@PathVariable Integer idShift) {
        return shiftService.getShift(idShift)
                .map(s -> {
                    shiftService.deleteShift(idShift);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
