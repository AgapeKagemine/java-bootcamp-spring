package com.bootcamp.trainjdbc.repository;

import java.util.List;

import com.bootcamp.trainjdbc.model.Account;

public interface AccountRepository {
    public int save(Account account);
    public int update(Account account);
    public Account findById(Integer id);
    public List<Account> findAll();
    public boolean existsById(Integer id);
    public int deleteById(Integer Id);
    public int deposit(float amount, Integer id);
    public int withdraw(float amount, Integer id);  
}