package com.bootcamp.testjpa.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bootcamp.testjpa.model.Account;
import com.bootcamp.testjpa.repository.AccountRepository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account save(Account account) {
        return jdbcTemplate.update("INSERT INTO accounts (account_number, balance) VALUES(?,?)", new Object[] { account.getAccountNumber(), account.getBalance() });
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE accounts SET account_number=?, balance=? WHERE id=?", new Object[] { account.getAccountNumber(), account.getBalance(), account.getId() });
    }

    @Override
    public Account findById(Integer id) {
        Account account = jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE id=?", BeanPropertyRowMapper.newInstance(Account.class), id);
        return account;
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT * from accounts", BeanPropertyRowMapper.newInstance(Account.class));
    }

    @Override
    public boolean existsById(Integer id) {
        Integer count = this.jdbcTemplate.queryForObject("select count(*) from accounts WHERE id=?", Integer.class, id);
        if(count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM accounts WHERE id=?", id);
    }

    @Override
    public int deposit(float amount, Integer id) {
        return jdbcTemplate.update("UPDATE accounts SET balance=balance + ? WHERE id=?", new Object[] { amount, id });
    }

    @Override
    public int withdraw(float amount, Integer id) {
        return jdbcTemplate.update("UPDATE accounts SET balance=balance - ? WHERE id=?", new Object[] { amount, id });
    }

}
