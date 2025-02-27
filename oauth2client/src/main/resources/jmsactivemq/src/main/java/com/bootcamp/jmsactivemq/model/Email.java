package com.bootcamp.jmsactivemq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {
    private String to;
    private String body;
}
