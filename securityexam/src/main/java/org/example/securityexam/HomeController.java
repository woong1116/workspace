package org.example.securityexam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/fail")
    public String fail(){
        return "fail ㅠㅠ";
    }
}
