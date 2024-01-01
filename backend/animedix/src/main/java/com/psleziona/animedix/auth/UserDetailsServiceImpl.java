package com.psleziona.animedix.auth;

import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.repository.ClientRepository;
import com.psleziona.animedix.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client c = clientRepository.findClientByEmail(username).orElseGet(() -> null);
        Employee e = employeeRepository.findEmployeeByEmail(username).orElseGet(() -> null);
        if(c != null)
            return new CustomUserDetails(username, c.getPassword(), c.getRole());

        if(e != null)
            return new CustomUserDetails(username, e.getPassword(), e.getRole());

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
