package com.bootcamp.threadpooltaskexecutor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.threadpooltaskexecutor.model.GithubUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SyncService {

    @Autowired
    RestTemplate restTemplate;

    public GithubUser findUserSync(String user) throws InterruptedException{
        log.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        
        GithubUser results = restTemplate.getForObject(url, GithubUser.class);
        // Artificial delay of 1s for demonstration purposes
        log.info("artificial delay of 2s.....");
        Thread.sleep(2000L);
        return results;
    }
}
