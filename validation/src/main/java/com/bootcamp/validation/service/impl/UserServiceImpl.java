package com.bootcamp.validation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.bootcamp.validation.model.User;
import com.bootcamp.validation.repo.UserRepository;
import com.bootcamp.validation.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repo;

    @Override
    public List<User> listAll() {
        return repo.findAll();
    }

    @Override
    public User get(String userId) {
        return repo.findById(userId).get();
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public void delete(String userId) {
        repo.deleteById(userId);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        logger.error("handle MethodArgumentNotValidException", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors.toString());
        return createResponseEntity(errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
}
