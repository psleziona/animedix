package com.psleziona.animedix.auth;

import com.psleziona.animedix.config.JwtService;
import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Role;
import com.psleziona.animedix.model.User;
import com.psleziona.animedix.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest httpServletRequest;
    private final UserRepository userRepository;

    public AuthResponse register(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        Client createdClient = clientService.setClient(client);
        CustomUserDetails userDetails = new CustomUserDetails(createdClient.getEmail(), createdClient.getPassword(), Role.CLIENT);
        String token = jwtService.generateToken(userDetails, client.getRole().toString(), client.getId());
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(@NotNull String email, @NotNull String password) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User u = userRepository.findByEmail(email).orElseGet(() -> null);
        CustomUserDetails user = null;
        if(u != null)
            user = new CustomUserDetails(u.getEmail(), u.getPassword(), u.getRole());
        else
            throw new UsernameNotFoundException("User not found with username: " + email);

        var token = jwtService.generateToken(user, user.getRole(), u.getId());
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        return authenticate(authRequest.getEmail(), authRequest.getPassword());
    }

    public User getSessionUser() {
        String token = getSessionToken();
        String prefix = "Bearer";
        token = token.substring(prefix.length());
        String email = jwtService.extractUserName(token);
        return userRepository.findByEmail(email).get();
    }

    private String getSessionToken() {
        return httpServletRequest.getHeader("Authorization");
    }

    public void changePassword(String password) {
        Client user = (Client)getSessionUser();
        user.setPassword(passwordEncoder.encode(password));
        clientService.setClient(user);
    }
}