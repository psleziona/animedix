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
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ValidationService<Client> validationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Client client) {
        validationService.validate(client);
        return ResponseEntity.ok(authService.register(client));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/passwordChange")
    public ResponseEntity<Void> changePassword(@RequestBody AuthRequest request) {
        authService.changePassword(request.getPassword());
        return ResponseEntity.ok().build();
    }
}
