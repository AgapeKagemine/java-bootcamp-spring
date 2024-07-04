// package com.bootcamp.threadpooltaskexecutor.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.bootcamp.threadpooltaskexecutor.model.GithubUser;
// import com.bootcamp.threadpooltaskexecutor.service.AsyncService;

// import lombok.extern.slf4j.Slf4j;

// @RestController
// @Slf4j
// public class SyncController {
//     @Autowired
//     AsyncService service;
    
//     @GetMapping("/github/user-sync")
//     public ResponseEntity<List<GithubUser>> findGithubUserSync() throws Exception{
//         // Start the clock
//         long start = System.currentTimeMillis();

//         // Kick of multiple, asynchronous lookups
//         GithubUser page1 = service.findUserSync("PivotalSoftware");
        
//         GithubUser page2 = service.findUserSync("CloudFoundry");
        
//         GithubUser page3 = service.findUserSync("Spring-Projects");
        

//         // Wait until they are all done
        

//         // Print results, including elapsed time
//         log.info("Elapsed time: " + (System.currentTimeMillis() - start));
//         log.info("--> " + page1);
//         log.info("--> " + page2);
//         log.info("--> " + page3);
//         List<GithubUser> list = new ArrayList();
//         list.add(page1);
//         list.add(page2);
//         list.add(page3);
        
//         return ResponseEntity.ok(list);
//     }
// }
