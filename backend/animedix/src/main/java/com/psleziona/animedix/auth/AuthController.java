package com.psleziona.animedix.auth;

import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.violation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ValidationService<Client> validationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Client client) {
        validationService.validate(client);
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<AuthResponse> register(@RequestBody Employee employee) {
        validationService.validate(user);
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
