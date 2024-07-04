package com.bootcamp.testjpa.service;


import java.util.List;

import com.bootcamp.testjpa.model.Account;

public interface AccountService {
    List<Account> listAll();

    Account get(Integer id);

    Account save(Account account);

    Account deposit(float amount, Integer id);

    Account withdraw(float amount, Integer id);
    
    void delete(Integer id);
}
