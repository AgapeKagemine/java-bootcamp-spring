package com.bootcamp.restapi.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcamp.restapi.exception.AccountAlreadyExistsException;
import com.bootcamp.restapi.exception.NoSuchAccountExistsException;
import com.bootcamp.restapi.model.Account;
import com.bootcamp.restapi.repository.AccountRepository;
import com.bootcamp.restapi.service.AccountService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository repo;
    
    public List<Account> listAll() {
        return repo.findAll();
    }
    
    public Account get(Integer id) {
        try {
            return repo.findById(id).get();
        }catch(NoSuchElementException e) {            
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }
    
    public Account save(Account account){
        try {
            return repo.save(account);
        }catch(DataIntegrityViolationException e) {                
            throw new AccountAlreadyExistsException("Account already exists!", e);
        }catch(ConstraintViolationException e) {            
            throw new AccountAlreadyExistsException("Account already exists!", e);
        }
    }
    
    public Account deposit(float amount, Integer id) {
        if(!repo.existsById(id)) {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
        repo.deposit(amount, id);
        return repo.findById(id).get();
    }
    
    public Account withdraw(float amount, Integer id) {
        if(!repo.existsById(id)) {
            throw new NoSuchAccountExistsException("No Such Account exists!");  
        }
        repo.withdraw(amount, id);
        return repo.findById(id).get();
    }    
    
    public void delete(Integer id) {        
        if(!repo.existsById(id)) {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
        repo.deleteById(id);
    }
}
