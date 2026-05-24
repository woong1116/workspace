package org.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
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
