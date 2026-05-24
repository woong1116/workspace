package org.example.springmvc.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
//    @GetMapping("/form")
//    public String joinForm(){
////        회원가입 폼을 응답.
//        return "user_join_form";
//    }

    @GetMapping("/join")
    public String join(Model model){
        //회원가입 폼을 응답.
        model.addAttribute("user", new User());

        return "user/user_join_form";
    }
    @PostMapping("/join")
    public String join2(@Valid @ModelAttribute User user, BindingResult bindingResult){

//        입력된  값을 검증!!
        if(bindingResult.hasErrors()){
            return "user/user_join_form";
        }


//        회원가입로직 실행!!!
//        이름, 이메일, 패스워드 값을 얻어와서 log로 출력해보세요.

        log.info("name:{}",user.getName());
        log.info("password:{}",user.getPassword());
        log.info("email:{}",user.getEmail());
        return "redirect:/hi";
    }


//    로그인폼
//    로그인을 처리 -  hi 로 리다이렉트 되도록 구현해볼까요?

    @GetMapping("/list")
    public String list(){
        return null;
    }

    @GetMapping("/update")
    public String update(){
        return null;
    }


    @GetMapping("/")
    public String index(){
        return null;
    }


    @GetMapping("/delete")
    public String delete(){
        return null;
    }
}
