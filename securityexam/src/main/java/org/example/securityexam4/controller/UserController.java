package org.example.securityexam4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.securityexam4.domain.UserRegisterDTO;
import org.example.securityexam4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

//    회원가입을 한다면??  1. 회원가입폼 주세요.
    @GetMapping("/register")
    public String signup(){
        return "exam4/user/signup";
    }


//    2. 회원가입해주세요.
    @PostMapping("/register")
    public String join(@ModelAttribute UserRegisterDTO user){
        //이미 username 을 이용하는 사용자가 있는지 체크하고 간다면??  어떻게 구현하면 좋을지 고민해보기!!
        if(userService.existsUser(user.getUsername())){
            log.info("이미 사용중인 아이디 :: "+ user.getUsername());
            return "exam4/user/userreg-error";
        }

        userService.joinUser(user);
        return "redirect:/";
    }

//    로그인 폼을 요청하는 것 까지 구현해보세요.    1. controller 메서드추가 2. 화면 추가  3. 시큐리티 설정!!
    @GetMapping("/login")
    public String loginForm(){
        return "exam4/user/loginform";
    }


    @GetMapping("/myinfo")
    public String myInfo(){
        return "exam4/user/myinfo";
    }
}
//1. 회원가입 폼이 요청되었는데.. 지금은 인증하라고 나와요.  그런데 이것을 인증없이 보여지도록 설정!!
//2. 회원가입시에 이미 존재하는 username 이 있다면 다르게 처리하고 싶다면..  무엇을 구현해야할까요?