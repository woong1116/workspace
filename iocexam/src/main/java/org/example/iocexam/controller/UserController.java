package org.example.iocexam.controller;

import org.example.iocexam.domain.User;
import org.example.iocexam.service.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    public void joinUser(){
//        회원정보는 (실제 동작되는 서비스에서는 ) 사용자로 부터 얻어올거예요.
        User user=new User();
        user.setName("carami");
        user.setPassword("123456");
        user.setEmail("carami@gmail.com");

//        실제 로직은 서비스에 부탁해서 회원 가입처리를 하겠죠?
        userService.joinUser(user);
    }
}
