package org.example.oauthexam.config;

import lombok.RequiredArgsConstructor;
import org.example.oauthexam.security.CustomOAuth2AuthenticationSuccessHandler;
import org.example.oauthexam.service.SocialUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SocialUserService socialUserService;
    private final CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,CorsConfigurationSource configurationSource)throws Exception{
        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/","/userregform").permitAll()
                        .requestMatchers("/oauth2/**","/login/oauth2/code/github","/saveSocialUser","/registerSocialUser").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf->csrf.disable())
                .formLogin(form->form
                        .loginPage("/loginform")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                .cors(cors->cors.configurationSource(configurationSource))
                .httpBasic(httpbasic->httpbasic.disable());


        http
                .oauth2Login(oauth2->oauth2
                        .loginPage("/loginform")
                        .failureUrl("/loginFailure")
                        .userInfoEndpoint(userInfo->userInfo
                                .userService(this.oAuth2UserService())
                        )
                        .successHandler(customOAuth2AuthenticationSuccessHandler)
                );
        http
                .logout(logout->logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );


        return http.build();
    }


    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(){
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return oauth2UserRequest->{
            OAuth2User oAuth2User = delegate.loadUser(oauth2UserRequest);

            //소셜로그인이 될때 해야할일 구현!!!
            String provider = oauth2UserRequest.getClientRegistration().getRegistrationId();
//            아래 코드처럼 형변환 할 때는 get("id")  꺼낸값이 null 이면, 아래 코드는 실행될 때 어떤일이 일어날까요?
//            String socialId = (String)oAuth2User.getAttributes().get("id");
            String socialId = String.valueOf(oAuth2User.getAttributes().get("id"));
            String username = String.valueOf(oAuth2User.getAttributes().get("login"));
            String email = String.valueOf(oAuth2User.getAttributes().get("email"));
            String avatarUrl = String.valueOf(oAuth2User.getAttributes().get("avatar_url"));

            socialUserService.saveOrUpdateUser(socialId,provider,username,email,avatarUrl);

            return oAuth2User;
        };

    }



    //여기서는 모두 허용하는 것으로 설정함.  실제 서비스에서는 적절하게 값들을 넣어주어야함.
    @Bean
    public CorsConfigurationSource configurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",config);
        return source;
    }

}
