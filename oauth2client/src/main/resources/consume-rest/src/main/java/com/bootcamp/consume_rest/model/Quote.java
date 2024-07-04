package com.bootcamp.consume_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Quote {
    String type;
    Value Value;
}
