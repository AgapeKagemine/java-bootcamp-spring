package com.bootcamp.weekly.service.impl.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bootcamp.weekly.entity.role;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(role.USER.name())) {
            return User.withUsername(role.USER.name())
                        .password(new BCryptPasswordEncoder().encode(role.USER.name()))
                        .roles(role.USER.name())
                        .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}
