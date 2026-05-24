package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class HelloController {
    @GetMapping("/hi")
    public String hello(){


        return "welcome";
    }
    @GetMapping("/hispring")
    public String hiSpring(){
        return "spring";
    }
    @GetMapping("/rest")
    @ResponseBody
    public String rest(){
        return "Hello rest!!!";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request){
        String name = request.getParameter("name");
        request.setAttribute("name", name);
        request.setAttribute("message","Welcome to Spring MVC!");
        request.setAttribute("timestamp", LocalDateTime.now());
        System.out.println(name);
//        log.info(request.)
//        HttpServletRequest -- 요청정보를 추상화 한 객체!!!
        return "home";
    }

    @GetMapping("/home")
    public String home2(@RequestParam("name") String name,Model model ){
        model.addAttribute("name",name);
        model.addAttribute("message","Welcome to Spring MVC!");
        model.addAttribute("timestamp", LocalDateTime.now());
        log.info("name::"+name);
        return "home";
    }
}
