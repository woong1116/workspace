package org.example.myspringbootapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")  // 프론트엔드에서 API 호출을 위한 CORS 설정
@RestController
public class HelloController {
    @RequestMapping(path = "/hello")
    public String hello() {
        return "Hello Kubernetes!";
    }
}