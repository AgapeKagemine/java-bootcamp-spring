package com.bootcamp.restapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.restapi.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("UPDATE Account a SET a.balance = a.balance + ?1 WHERE a.id = ?2")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public void deposit(float amount, Integer id);

    @Query("UPDATE Account a SET a.balance = a.balance - ?1 WHERE a.id = ?2")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public void withdraw(float amount, Integer id);  
}