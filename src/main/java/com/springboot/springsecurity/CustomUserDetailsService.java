package com.springboot.springsecurity;

import com.springboot.springsecurity.User;
import com.springboot.springsecurity.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from database
        User user = userRepository.findByUsername(username);
        if (user == null) 
        {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

       
        // Return Spring Security User object
        return org.springframework.security.core.userdetails.User
        		.withUsername(user.getUsername())
        		.password("{noop}"+user.getPassword())
        		.disabled(!user.isEnabled())
        		.authorities("USER")
        		.build();
    }
}