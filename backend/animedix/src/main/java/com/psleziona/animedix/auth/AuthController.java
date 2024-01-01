package com.psleziona.animedix.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    String d() {
        return "Work";
    }

    @PostMapping("/login")
    ResponseEntity<Void> login(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
