package com.bootcamp.trainjdbc.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.trainjdbc.model.Account;
import com.bootcamp.trainjdbc.repository.AccountRepository;
import com.bootcamp.trainjdbc.service.AccountService;

@Service
@Transactional 
public class AccountServiceImpl implements AccountService {
    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository repo;

    public List<Account> listAll() {
        return repo.findAll();
    }

    public Account get(Integer id) {
        return repo.findById(id);
    }

    public Account save(Account account){
        repo.save(account);
        return account;
        // int updated =  repo.save(account);
        // if(updated > 0) {
        //     return repo.findById(account.getId());
        // } else {
        //     return null;
        // }
    }

    public Account deposit(float amount, Integer id) {
        int updated = repo.deposit(amount, id);
        if(updated > 0) {
            return repo.findById(id);
        } else {
            return null;
        }
    }

    public Account withdraw(float amount, Integer id) {
        int updated = repo.withdraw(amount, id);
        if(updated > 0) {
            return repo.findById(id);
        } else {
            return null;
        }
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}