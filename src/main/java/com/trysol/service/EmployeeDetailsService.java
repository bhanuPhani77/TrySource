package com.trysol.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trysol.entity.EmployeeDetails;

import jakarta.annotation.PostConstruct;
@Service
public class EmployeeDetailsService implements UserDetailsService{
	private final PasswordEncoder passwordEncoder;
    private final Map<String, EmployeeDetails> userRegistry = new HashMap<>();

    public EmployeeDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        userRegistry.put("user", new EmployeeDetails.Builder().withFirstName("bhanu")
            .withLastName("phani")
            .withEmail("bhannu@email.com")
            .withUsername("phani")
            .withPassword(passwordEncoder.encode("test123"))
            .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
            .build());
        userRegistry.put("admin", new EmployeeDetails.Builder().withFirstName("varma")
            .withLastName("gurrala")
            .withEmail("varma@email.com")
            .withUsername("varma")
            .withPassword(passwordEncoder.encode("password123"))
            .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
            .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final EmployeeDetails userDetails = userRegistry.get(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException(username);
        }
        return userDetails;
    }

}
