package org.example.springmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.domain.Item;
import org.example.springmvc.domain.User;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/item")
@Slf4j
public class ItemController {
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("item", new Item());
        return "item/item_form.html";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item item){
        log.info("item name : {}",item.getName());
        log.info("item price : {}", item.getPrice());
        return "redirect:/hi";
    }

    @GetMapping("/list")
    public String list(Model model){
//        실제 동작할 때는 service 한테 얻어오겠죠??
        List<Item> items = Arrays.asList(
            new Item("사과",2000),
            new Item("오렌지", 4000),
                new Item("망고", 5000),
                new Item("수박", 6000)
        );

        model.addAttribute("items",items);
        return "item/list";

    }

}
