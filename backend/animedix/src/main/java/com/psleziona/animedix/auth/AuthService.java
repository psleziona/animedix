package com.psleziona.animedix.auth;

import com.psleziona.animedix.config.JwtService;
import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Role;
import com.psleziona.animedix.service.ClientService;
import com.psleziona.animedix.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest httpServletRequest;

    public AuthResponse register(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        Client createdClient = clientService.setClient(client);
        CustomUserDetails userDetails = new CustomUserDetails(createdClient.getEmail(), createdClient.getPassword(), Role.CLIENT);
        String token = jwtService.generateToken(userDetails);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(@NotNull String email, @NotNull String password) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        Client c = clientService.getClientByEmail(email).orElseGet(() -> null);
        Employee e = employeeService.getEmployeeByEmail(email).orElseGet(() -> null);
        CustomUserDetails user = null;
        if(c != null)
            user = new CustomUserDetails(email, c.getPassword(), c.getRole());

        if(e != null)
            user = new CustomUserDetails(email, e.getPassword(), e.getRole());

        if(c == null && e == null)
            throw new UsernameNotFoundException("User not found with username: " + email);

        var token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        return authenticate(authRequest.getEmail(), authRequest.getPassword());
    }

    public Client getSessionClient() {
        String token = getSessionToken();
        String prefix = "Bearer";
        token = token.substring(prefix.length());
        String email = jwtService.extractUserName(token);
        return clientService.getClientByEmail(email).get();
    }

    public Employee getSessionEmployee() {
        String token = getSessionToken();
        String prefix = "Bearer";
        token = token.substring(prefix.length());
        String email = jwtService.extractUserName(token);
        return employeeService.getEmployeeByEmail(email).get();
    }

    private String getSessionToken() {
        return httpServletRequest.getHeader("Authorization");
    }
}