package com.bootcamp.weekly.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTReponse implements Serializable {

    private final String jwttoken;

}