package com.bootcamp.simpleapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bootcamp.simpleapplication.repo")
@EntityScan("com.bootcamp.simpleapplication.model")
public class SimpleapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleapplicationApplication.class, args);
	}

}
