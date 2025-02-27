package com.bootcamp.security.service.impl;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.io.Serializable;;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        log.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message;

        if (authException.getCause() != null) {
            message = authException.getCause().toString() + " " + authException.getMessage();
        } else {
            message = authException.getMessage();
        }

        byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));

        response.getOutputStream().write(body);
    }
}
