package org.example.hellospring.controller;

import org.example.hellospring.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

//    @PostMapping
//    @GetMapping
//    @PutMapping
//    @PatchMapping
//    @DeleteMapping

    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("1234");
        user.setPhone("010-1234-5678");
        user.setEmail("123@email.com");
        return user;
    }
}
