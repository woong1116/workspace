package org.example.securityexam4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home!!";
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
