package org.example.springmvc.controller;

import jakarta.validation.Valid;
import org.example.springmvc.domain.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserFormController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "user/register";  // 폼 재표시
        }

//        User savedUser = userService.register(user);
//        UserDto savedUser = new UserDto();
//        savedUser.
        redirectAttrs.addFlashAttribute("message",
                "Registration successful!");

        return "redirect:/users/" + 10;
    }
}
