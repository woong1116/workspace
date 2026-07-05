package org.example.oauthexam.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.oauthexam.entity.SocialLoginInfo;
import org.example.oauthexam.entity.User;
import org.example.oauthexam.service.SocialLoginInfoService;
import org.example.oauthexam.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final SocialLoginInfoService socialLoginInfoService;

    private String extractProviderFromUri(String uri){
        // /login/oauth2/code/{registrationid}
        // /login/oauth2/code/github/    --   github
        if(uri==null || uri.isBlank())
            return null;

        int index = uri.indexOf("/login/oauth2/code/");
        if(index == -1)
            return null;

        String provider = uri.substring(index + "/login/oauth2/code/".length());
        if(provider.endsWith("/")){
            provider = provider.substring(0, provider.length() - 1);
        }
        return provider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //요청정보로부터 provider를 얻어온다.
//       redirect-uri : /login/oauth2/code/{registrationid}  github
        String requestURI = request.getRequestURI();
//        provider (ex: github) 를 얻어온다.
        String provider = extractProviderFromUri(requestURI);

//        잘못된 요청이므로...
        if(provider == null){
            response.sendRedirect("/");
            return;
        }

//        authentication 으로 부터 정보를 꺼내봐요.
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) token.getPrincipal();

        //깃헙이 정한 속성 id, name 을 추출
        String socialId = String.valueOf(oauth2User.getAttributes().get("id"));
//        소셜 정보가 비공개로 되어있다면 정보가 추출되지 않아요.
// 소셜마다, name, login, user
//        String name = String.valueOf(oauth2User.getAttributes().get("name"));
        String name = String.valueOf(oauth2User.getAttributes().getOrDefault("name", oauth2User.getAttributes().getOrDefault("login", "user")));


//        기존 사용자인지를 확인해서...   (우리 어플리케이션에 처음 가입한 사용자라면 추가 정보를 받고싶기도..)
        Optional<User> foundUser = userService.findByProviderAndSocialId(provider, socialId);

        //기존사용자라면 User에 정보가 있었겠죠?
        if(foundUser.isPresent()){
            User user = foundUser.get();

//            우리 서비스에 알맞게 정보를 채워줘야겠죠?
            CustomUserDetails customUserDetails = new CustomUserDetails(
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getRoles().stream().map(role->"ROLE_"+role.getName()).collect(Collectors.toList())
            );

            Authentication newAuth = new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(newAuth);
            response.sendRedirect("/welcome");
            return;
        }

        //우리 어플리케이션에 처음 방문한 사용자에 대한 처리!! 최소 정보만 여기서 저장하고, 추가 정보를 사용자로부터 입력받도록 화면이동 할거예요!!
//        시간!!  정해진 시간보다 더 늦게 사용자가 추가 정보를 입력하면..  오류내기로 했어요.
        SocialLoginInfo socialLoginInfo = socialLoginInfoService.saveSocialLoingInfo(provider, socialId);

        String redirect = "/registerSocialUser?provider="+provider
                +"&socialId="+ url(socialId)
                +"&name="+url(name)
                +"&uuid="+url(socialLoginInfo.getUuid());

        response.sendRedirect(redirect);
    }
    private String url(String s){
        return URLEncoder.encode(s==null?"":s, StandardCharsets.UTF_8);
    }
}
