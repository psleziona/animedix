package com.psleziona.animedix.controller;

import com.psleziona.animedix.service.AssortmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AssortmentController {
    private final AssortmentService assortmentService;
}
