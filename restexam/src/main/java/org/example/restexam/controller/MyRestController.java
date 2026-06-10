package org.example.restexam.controller;

import org.example.restexam.dto.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MyRestController {
    @GetMapping("/hi")
    public String hi(){
        return "hi rest Controller";
    }

    @GetMapping("/greeting")
    public Map<String,String> greet(@RequestParam(name="name",required = false, defaultValue = "carami")String name){

        Map<String, String> response = new HashMap<>();
        response.put("name", name);

        return response;
    }

    @PostMapping ("/users")
    public User createUser(@RequestBody User user){
        user.setId(1L);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }
}
