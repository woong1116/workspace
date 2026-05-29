package org.example.springmvc.controller.exam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController {


    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "손") String name,
                           Model model) {
        model.addAttribute("message", "안녕하세요, " + name + "님!");

        return "greeting";
    }
}