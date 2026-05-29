package org.example.springmvc.controller.exam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class CookieController {
//    사용자로부터 상태정보로 저장 하고 싶은 값을 받아오는 것으로!!  (실제는 서버에서 처리 할꺼예요.)
    @GetMapping("/cookieForm")
    public String cookieForm(){
        return "cookie_form";
    }
    @GetMapping("/addcookie")
    public String addcookie(@RequestParam String cookieName,
                            @RequestParam String cookieValue, HttpServletResponse response){
        log.info("cookieName - {}, cookieValue - {}", cookieName, cookieValue);
//        상태정보를 유지할 쿠키객체 생성.
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);

//        반드시 응답객체에 실어서 클라이언트(브라우저)에 전달 해야함.
        response.addCookie(cookie);

        return "redirect:/cookieView";
    }

    @GetMapping("/cookieView")
    public String cookieView(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        List<String> cookieList = new ArrayList<>();

        if(cookies != null){
            for(Cookie cookie : cookies){
//                if("loginOk".equals(cookie.getName()))
                log.info(cookie.getName()+":::"+cookie.getValue());
                cookieList.add(cookie.getName()+":::"+cookie.getValue());
            }
        }
        model.addAttribute("cookieList",cookieList);

        return "cookie_view";
    }

    @GetMapping("/cookieDel")
    public String cookieDel(HttpServletResponse response){
        Cookie cookie = new Cookie("1111","");
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "redirect:/cookieView";
    }

//   1. 로그인 폼 보여주세요.
    @GetMapping("/login")
    public String loginForm(){
        return "login_form";
    }
// 2. 로그인해주세요.
    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String passwd,HttpServletResponse res){
        //서비스단에 보내서 로그인 여부를 체크하겠죠??
//        서비스 쪽에서 true, false 를 리턴하든 알려주겠죠?
//        아이디와 패스워드가 정해진 값과 일치하다면 로그인 처리!!! (쿠키생성)
//        아이디 패스워드가 일치하지 않다면 로그인 폼으로 리다이렉트!!
        if("woong".equals(id)&&"1234".equals(passwd)){
//            로그인을 시켜줘야해요. (상태정보유지!!!)  --  쿠키를 통해서 유지!!
            Cookie cookie = new Cookie("loginCookie",id);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*365);

            res.addCookie(cookie);

            return "redirect:/userList";
        }else {

            return "redirect:/login";
        }
    }
//    3. UserList 를 요청해요.  -  로그인한 사용자라면 UserList를 보여주고,
//    그렇치 않은 사용자라면 로그인 폼 화면으로 리다이렉트
    @GetMapping("/userList")
    public String userList(@CookieValue(value = "loginCookie", required = false) String id){
        if(id != null){
            return "user_list";
        }
        return "redirect:/login";

    }


//    4. 로그아웃 요청이 들어오면 쿠키를 삭제하는 로직을 구현해주세요.
//    logout이 완료되면 loginform 으로 리다이렉트 되도록.
    @GetMapping("/logout")
    public String logout(HttpServletResponse res){
        Cookie cookie = new Cookie("loginCookie","");
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);

        return "redirect:/userList";
    }


//    html 은 1. 로그인폼  2. userList 화면

    @GetMapping("/counter")
    public String counter(@CookieValue(value = "visitCount", defaultValue = "0") int visitCount, HttpServletResponse response, Model model) {
        visitCount++;

        Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount));
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

        model.addAttribute("visitCount", visitCount);

        return "exam/counter";
    }

}
