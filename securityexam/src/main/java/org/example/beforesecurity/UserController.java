package org.example.beforesecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/test")
    public String test(){
        System.out.println("UserController test() ");
        System.out.println("필터에서 전달한 값 꺼내보기 :: "+UserContext.getUser().getName());

        userService.threadLocalTest();
        
        return "test";
    }
}
