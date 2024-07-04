package com.bootcamp.restapi.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bootcamp.restapi.model.Account;
import com.bootcamp.restapi.repository.AccountRepository;

@Configuration
public class LoadDatabase {
    private AccountRepository accountRepository;

    public LoadDatabase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            accountRepository.saveAll(List.of(
                new Account("1982080188", 1021.99f),
                new Account("1982032187", 231.50f),
                new Account("1982094328", 6211.00f)
            ));
        };
    }
}
