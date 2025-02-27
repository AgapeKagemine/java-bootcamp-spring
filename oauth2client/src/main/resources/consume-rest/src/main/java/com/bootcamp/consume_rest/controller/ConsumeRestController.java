package com.bootcamp.consume_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.consume_rest.model.Quote;

@RestController
@RequestMapping("/api/quotes")
public class ConsumeRestController {
    @Autowired
    RestTemplate restTemplate;
    
    static final String API_QUOTE_URL = "http://localhost:8090/api";
    
    @GetMapping
    public HttpEntity<Quote[]> allQuotes(){
        ResponseEntity<Quote[]> responseEntity = restTemplate.getForEntity(API_QUOTE_URL, Quote[].class);
        Quote[] objects = responseEntity.getBody();
        return new ResponseEntity<>(objects, HttpStatus.OK);
        
    }
    
    @GetMapping("/random")
    public ResponseEntity<Quote> getQuoteRandom(){
        Quote quote = restTemplate.getForObject(
                API_QUOTE_URL.concat("/random"), Quote.class);
        return new ResponseEntity<>(quote, HttpStatus.OK);
        
    }

    @GetMapping("/list")
    public ResponseEntity<?> listQuotes(){
        try {
            ResponseEntity<List<Quote>> responseEntity = restTemplate.exchange(API_QUOTE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Quote>>(){});
            List<Quote> quotes = responseEntity.getBody();
            return ResponseEntity.ok(quotes);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
