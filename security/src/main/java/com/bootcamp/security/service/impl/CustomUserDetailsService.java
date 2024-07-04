package com.bootcamp.security.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bootcamp.security.model.role;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(role.ADMIN.name())) {
            UserDetails user = User.withUsername(role.ADMIN.name())
                    .password(new BCryptPasswordEncoder().encode(role.ADMIN.name()))
                    .roles(role.ADMIN.name())
                    .build();
             return user;
        }
        
        if (username.equals(role.USER.name())) {
            UserDetails user = User.withUsername(role.USER.name())
                    .password(new BCryptPasswordEncoder().encode(role.USER.name()))
                    .roles(role.USER.name())
                    .build();
             return user;
        }
        
        if (username.equals(role.MODERATOR.name())) {
            UserDetails user = User.withUsername(role.MODERATOR.name())
                    .password(new BCryptPasswordEncoder().encode(role.MODERATOR.name()))
                    .roles(role.MODERATOR.name())
                    .build();
             return user;
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}
