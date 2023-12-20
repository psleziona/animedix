package com.psleziona.animedix.controller;

import com.psleziona.animedix.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("test")
    public String d() {
        return "ddd";
    }
}
