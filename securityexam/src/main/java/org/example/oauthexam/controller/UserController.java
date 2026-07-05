package org.example.oauthexam.controller;

import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.oauthexam.dto.SocialUserRequestDto;
import org.example.oauthexam.entity.Role;
import org.example.oauthexam.entity.SocialLoginInfo;
import org.example.oauthexam.entity.User;
import org.example.oauthexam.security.CustomUserDetails;
import org.example.oauthexam.service.SocialLoginInfoService;
import org.example.oauthexam.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final SocialLoginInfoService socialLoginInfoService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "oauth/welcome";
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "oauth/user/loginform";
    }

    @GetMapping("/myinfo")
    public String info(@AuthenticationPrincipal Object customUserDetails, Model model) {
        model.addAttribute("user", customUserDetails);
        return "oauth/info";
    }

    @GetMapping("/registerSocialUser")
    public String registrationSocialUser(@ModelAttribute SocialUserRequestDto requestDto, Model model) {
        //registerSocialUser?provider="+provider+"&socialId="+ url(socialId)+"&name="+url(name)+"&uuid="+url(socialLoginInfo.getUuid());
        model.addAttribute("provider", requestDto.getProvider());
        model.addAttribute("socialId", requestDto.getSocialId());
        model.addAttribute("name", requestDto.getName());
        model.addAttribute("uuid", requestDto.getUuid());

        return "oauth/user/registerSocialUser";

    }

    @PostMapping("/saveSocialUser")
    public String saveSocialUser(@ModelAttribute SocialUserRequestDto requestDto) {
        //시간체크!!
        Optional<SocialLoginInfo> byProdAndUuidAndSocialId
                = socialLoginInfoService.findByProdAndUuidAndSocialId(requestDto.getProvider(), requestDto.getUuid(), requestDto.getSocialId());


        if (byProdAndUuidAndSocialId.isPresent()) {
            SocialLoginInfo socialLoginInfo = byProdAndUuidAndSocialId.get();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(socialLoginInfo.getCreatedAt(), now);
            if (duration.toMinutes() > 20) {
                return "redirect:/error";  //20분이상 경과했다면 에러페이지로 이동.
            }

            User saveUser = userService.saveUser(requestDto);

            //우리 시큐리티에게 Authentication 만들어서 넣어줘야겠죠?
            try {
                List<String> roles = saveUser.getRoles().stream().map(Role::getName).collect(Collectors.toList());

                CustomUserDetails customUserDetails = new CustomUserDetails(
                        saveUser.getUsername(),
                        saveUser.getPassword(),
                        saveUser.getName(),
                        roles
                );
                Authentication newAuth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);


            } catch (Exception e) {
                log.error("SecurityContext 갱신 중 오류 발생!! {}", e.getMessage());
            }
            return "redirect:/";

        } else {
            return "redirect:/error";
        }
    }

}
