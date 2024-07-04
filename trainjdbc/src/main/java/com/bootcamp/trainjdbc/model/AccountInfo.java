package com.bootcamp.trainjdbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountInfo {
    private Integer id;
    private Integer account_id;
    private String account_name;
    
    public AccountInfo(Integer account_id, String account_name) {
        this.account_id = account_id;
        this.account_name = account_name;
    }
}
