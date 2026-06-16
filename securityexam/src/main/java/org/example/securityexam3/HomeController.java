package org.example.securityexam3;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String home(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String message=null;
        if(authentication == null || !authentication.isAuthenticated()){
            message = "로그인된 사용자가 없습니다.";
            return message;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            message = "현재 로그인한 사용자 "+ userDetails.getUsername();
        }else {
            message = "현재 로그인한 사용자 "+ principal.toString();
        }
        return message;
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/abc")
    public String aaa(){
        return "aaa";
    }
    @GetMapping("/bbb")
    public String bbb(){
        return "bbb";
    }
    @GetMapping("/ccc")
    public String ccc(@AuthenticationPrincipal UserDetails userDetails){
        return "ccc  로그인 한 user :: "+userDetails.getUsername();
    }
}
