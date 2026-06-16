package org.example.securityexam3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/abc")
    public String aaa(){
        return "aaa";
    }
    @GetMapping("/bbb")
    public String bbb(){
        return "bbb";
    }
    @GetMapping("/ccc")
    public String ccc(){
        return "ccc";
    }
}
