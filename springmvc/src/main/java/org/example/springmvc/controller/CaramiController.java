package org.example.springmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class CaramiController {
    @GetMapping("/hi2")
    public String hi(){
        return "welcome";
    }

    @GetMapping("/index")
    public String index(@RequestParam(name="name",required = false,defaultValue = "guest") String name,
                        @RequestParam("message")String message, Model model){
        model.addAttribute("name", name);
        model.addAttribute("message", message);
        return "index";
    }

//    http://localhost:8080/index2?message=hello&name=carami   -- ?message=hello&name=carami(쿼리문자열)
//    https://www.google.com/search?q=%EC%9B%B9%EC%96%B4%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98+%EA%B5%AC%EC%A1%B0&oq=%EC%9B%B9%EC%96%B4%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98+%EA%B5%AC%EC%A1%B0&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIICAEQABgNGB4yCggCEAAYCBgNGB4yCggDEAAYCBgNGB4yBwgEEAAY7wUyCggFEAAYogQYiQUyCggGEAAYgAQYogTSAQg1ODk1ajBqN6gCCLACAfEFiaMdBmWxcIQ&sourceid=chrome&ie=UTF-8
    @GetMapping("/index2")
    public ModelAndView index2(@RequestParam(name="name",required = false,defaultValue = "guest") String name,
                               @RequestParam("message")String message){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", name);
        mv.addObject("message", message);
        return mv;
    }

//    https://v.daum.net/v/20260521085232331
//    https://v.daum.net/v/20260521114102678
//    https://v.daum.net/v/20260521080217869

    @GetMapping("/index3/{newsid}")
    public String index3(@PathVariable String newsid){
        log.info("newsid::"+newsid);

        return "welcome";
    }
}
