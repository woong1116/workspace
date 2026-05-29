package org.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLOutput;

@Controller
public class NavigationController {
    @GetMapping("internal")
    public String internal(Model model) {
        model.addAttribute("data", "ForwardData");

        return "forward:/destination";
    }

    @GetMapping("/destination")
    public String destination(Model model) {
        String data = (String) model.getAttribute("data");
        System.out.println(data);
        return "welcome";
    }
}
