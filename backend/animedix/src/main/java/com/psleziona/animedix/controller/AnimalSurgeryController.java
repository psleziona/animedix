package com.psleziona.animedix.controller;


import com.psleziona.animedix.service.AnimalSurgeryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AnimalSurgeryController {
    private final AnimalSurgeryService animalSurgeryService;
}
