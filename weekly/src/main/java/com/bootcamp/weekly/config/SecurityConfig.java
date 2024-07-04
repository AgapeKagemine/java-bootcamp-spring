package com.bootcamp.weekly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bootcamp.weekly.service.impl.security.CustomJWTAuthenticationFilter;
import com.bootcamp.weekly.service.impl.security.JwtAuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
	JwtAuthenticationEntryPoint jwtAuthenticatonEntryPoint;

    @Bean
    CustomJWTAuthenticationFilter customJwtAuthenticationFilter() {
		return new CustomJWTAuthenticationFilter();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {                
        http.csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticatonEntryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> {
                try {
                    auth.requestMatchers("/authenticate").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest()
                        .authenticated();
                } catch (Exception e) {
                    log.error("security filter chain failed", e);
                }});

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
