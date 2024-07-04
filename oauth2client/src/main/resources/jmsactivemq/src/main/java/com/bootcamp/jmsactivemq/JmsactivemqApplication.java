package com.bootcamp.jmsactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsactivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsactivemqApplication.class, args);
	}
	
}
