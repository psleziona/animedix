package com.psleziona.animedix.auth;

import com.psleziona.animedix.model.Client;
import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.User;
import com.psleziona.animedix.repository.ClientRepository;
import com.psleziona.animedix.repository.EmployeeRepository;
import com.psleziona.animedix.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(username).orElseGet(() -> null);
        if(u != null)
            return new CustomUserDetails(username, u.getPassword(), u.getRole());

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
