package com.bootcamp.trainjdbc.repository;

import java.util.List;

import com.bootcamp.trainjdbc.model.AccountInfo;

public interface AccountInfoRepository {
    public int save(AccountInfo accountInfo);

    public List<AccountInfo> findAll();
}
