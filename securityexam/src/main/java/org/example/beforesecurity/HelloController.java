package org.example.beforesecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @GetMapping("/api/hello")
    public String hello(){
        log.info("HelloController hello() 실행!!");
        System.out.println("HelloController hello() 실행!!");
        return "hello";
    }

    @GetMapping("/api/hi")
    public String hi(){
        System.out.println("HelloController hi() 실행!!");
        return "hello";
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("HelloController test() 실행!!");
        return "hello";
    }

}
