package com.psleziona.animedix.controller;

import com.psleziona.animedix.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;
}
