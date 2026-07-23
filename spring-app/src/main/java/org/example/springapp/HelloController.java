package org.example.springapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello 김지수";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}