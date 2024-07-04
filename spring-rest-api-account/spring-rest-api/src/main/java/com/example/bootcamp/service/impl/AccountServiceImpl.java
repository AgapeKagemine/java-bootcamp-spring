package com.example.bootcamp.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.bootcamp.exception.AccountAlreadyExistsException;
import com.example.bootcamp.exception.NoSuchAccountExistsException;
import com.example.bootcamp.model.Account;
import com.example.bootcamp.repository.AccountRepository;
import com.example.bootcamp.service.AccountService;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    
    @Autowired
    private AccountRepository repo;
    
    @Override
    public List<Account> listAll() {
        return repo.findAll();
    }
    
    @Override
    public Account get(Integer id) {
        try {
            return repo.findById(id).get();
        }catch(NoSuchElementException e) {            
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }
    
    @Override
    public Account save(Account account){
        try {
            return repo.save(account);
        }
        catch(DataIntegrityViolationException e){           
            logger.info("DataIntegrityViolationException" + e);
            throw new AccountAlreadyExistsException("Account already exists!", e);
        }
        catch (ConstraintViolationException e) { 
            logger.info("ConstraintViolationException"+e);
            throw new NoSuchAccountExistsException("awokawok", e);
        }
    }
    
    @Override
    public Account deposit(float amount, Integer id) {
        boolean isExists = repo.existsById(id);
        
        if(isExists) {
            repo.deposit(amount, id);
            return repo.findById(id).get();
        } else {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }
    
    @Override
    public Account withdraw(float amount, Integer id) {
        boolean isExists = repo.existsById(id);
        
        if(isExists) {
            repo.withdraw(amount, id);
            return repo.findById(id).get();
        } else {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }    
    
    @Override
    public void delete(Integer id) {
        boolean isExists = repo.existsById(id);
        
        if(isExists) {
            repo.deleteById(id);
        } else {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }

    public Logger getLogger() {
        return logger;
    }

}