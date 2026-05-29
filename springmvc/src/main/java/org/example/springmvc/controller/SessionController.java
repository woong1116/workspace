package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.springmvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes({"visitCount","cart","loginUser"})
public class SessionController {
    @ModelAttribute("visitCount")    //위에서 선언한 SessionAttribute를 초기화 하는 부분!!
    public int getVisitCount() {
        return 0;
    }

    @ModelAttribute("cart")
    public ArrayList getCart() {
        return new ArrayList<>();
    }
    @ModelAttribute("loginUser")
    public User getLoginUser() {
        return new User();
    }
    @GetMapping("/visit")
    public String visitPage(@ModelAttribute(name="visitCount")int visitCount, Model model) {
        visitCount++;
        model.addAttribute("visitCount", visitCount);

        return "visit";
    }
//    상태정보로 유지하고 싶은 값을 받아오는 화면 요청
    @GetMapping("/sessionForm")
    public String sessionForm() {
        return "session_form";
    }

    @GetMapping("/addSession")
    public String addSession(@RequestParam("sessionKey") String sessionKey,
                             @RequestParam("sessionValue")String sessionValue,
                             HttpSession session) {

        session.setAttribute(sessionKey, sessionValue);
        return  "redirect:/sessionView";
    }
    @GetMapping("/sessionView")
    public String sessionView(HttpSession session, Model model) {

//        세션이 저장한 모든 값을 출력한다라고 하면..
        Map<String, Object> map = new HashMap<String, Object>();

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = session.getAttribute(attributeName);
            map.put(attributeName, attributeValue);
        }

        model.addAttribute("session", map);
//        System.out.println(map.size());
        return "session_view";

    }

    @GetMapping("/sessionDel")
    public String sessionDel(@RequestParam("sessionKey") String sessionKey,HttpSession session) {
        session.removeAttribute(sessionKey);

        session.invalidate();   // 세션 전체 삭제!!
        return "redirect:/sessionView";
    }


    @GetMapping("/listView")
    public String listView(HttpSession session, Model model) {
        Object attribute = session.getAttribute("login");
//        로그인한 사용자에게만, welcome 페이지로 이동하고,
        if(attribute!=null){
            return "welcome";
        }else {

//        로그인하지 않은 사용자는 세션등록 페이지로 이동하게 코드를 만들어 볼까요?
            return "redirect:/sessionForm";
        }
    }

    @GetMapping("/listView2")
    public String listView2(@SessionAttribute(name="login",required = false) Object attribute) {
//        로그인한 사용자에게만, welcome 페이지로 이동하고,
        if(attribute!=null){
            return "welcome";
        }else {

//        로그인하지 않은 사용자는 세션등록 페이지로 이동하게 코드를 만들어 볼까요?
            return "redirect:/sessionForm";
        }
    }
}

