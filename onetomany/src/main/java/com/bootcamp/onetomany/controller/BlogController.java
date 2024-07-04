package com.bootcamp.onetomany.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.onetomany.model.Blog;
import com.bootcamp.onetomany.model.Owner;
import com.bootcamp.onetomany.repository.BlogRepository;
import com.bootcamp.onetomany.repository.OwnerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/saveOwner")
    public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
        log.info("Owner save called...");

        // list of Blog
        ArrayList<Blog> blogs = new ArrayList<>();
        
        for (Blog blogIn : owner.getBlogList()) {
            // new Blog
            Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
            // set owner to Blog
            blog.setOwner(owner);
            // add blog to list
            blogs.add(blog);
        }
        
        // add blog list to Owner
        owner.setBlogList(blogs);

        // save Owner
        Owner ownerOut = ownerRepository.save(owner);
        log.info("Owner out :: " + ownerOut);
        log.info("Saved!!!");
        return ResponseEntity.ok(ownerOut);
    }

    @PostMapping("/saveBlog")
    public ResponseEntity<Owner> saveBlog(@RequestParam(name = "id") String id) {
        log.info("Blog save called...");

        // fetch Ower
        Owner ownerTemp = ownerRepository.findById(Long.valueOf(id)).get();

        // list of Blog
        ArrayList<Blog> blogs = new ArrayList<>();

        // new Blog
        Blog blog = new Blog("Build application server using NodeJs", "nodeJs",
                "We will build REStful api using nodeJs.");
        // set owner to blog
        blog.setOwner(ownerTemp);
        // add Blog to list
        blogs.add(blog);

        blog = new Blog("Single Page Application using Angular", "Angular",
                "We can build robust application using Angular framework.");
        // set owner to blog
        blog.setOwner(ownerTemp);
        blogs.add(blog);

        // add Blog list to Owner
        ownerTemp.setBlogList(blogs);

        // save Owner
        Owner ownerSaved = ownerRepository.save(ownerTemp);

        log.info("Saved!!!");
        return ResponseEntity.ok(ownerSaved);
    }

    @GetMapping("/getOwner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable(name = "id") String id) {
        log.info("Owner get called...");

        // fetch Owner
        Owner ownerOut = ownerRepository.findById(Long.valueOf(id)).get();
        log.info("\nOwner details :: \n" + ownerOut);
        log.info("\nList of Blogs :: \n" + ownerOut.getBlogList());

        log.info("\nDone!!!");
        return ResponseEntity.ok(ownerOut);
    }
    
    @GetMapping("/getBlog/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable(name = "id") String id) {
        log.info("Blog get called...");

        // fetch Blog
        Blog blogOut = blogRepository.findById(Long.valueOf(id)).get();
        log.info("\nBlog details :: \n" + blogOut);
        log.info("\nOwner details :: \n" + blogOut.getOwner());

        log.info("\nDone!!!");
        return ResponseEntity.ok(blogOut);
    }
}