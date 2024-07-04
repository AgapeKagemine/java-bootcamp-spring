package com.bootcamp.restapi.service;

import java.util.List;

import com.bootcamp.restapi.model.Account;

public interface AccountService {
    List<Account> listAll();

    Account get(Integer Id);

    Account save(Account account);

    Account deposit(float amount, Integer Id);

    Account withdraw(float amount, Integer Id);

    void delete(Integer Id);
}
