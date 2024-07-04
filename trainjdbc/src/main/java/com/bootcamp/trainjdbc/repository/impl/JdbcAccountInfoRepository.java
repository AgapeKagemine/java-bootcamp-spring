package com.bootcamp.trainjdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bootcamp.trainjdbc.model.AccountInfo;
import com.bootcamp.trainjdbc.repository.AccountInfoRepository;

public class JdbcAccountInfoRepository implements AccountInfoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(AccountInfo accountInfo) {
        return jdbcTemplate.update("INSERT INTO accounts (id, account_id, account_name) VALUES(?,?,?)", new Object[] { accountInfo.getId(), accountInfo.getAccount_id(), accountInfo.getAccount_name() });
    }

    @Override
    public List<AccountInfo> findAll() {
        
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}
