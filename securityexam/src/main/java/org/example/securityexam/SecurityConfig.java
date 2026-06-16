package org.example.securityexam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
//   1. 기본설정!!  (아무런 설정도 안하면 이렇게 설정됨!!)
        http
                .authorizeHttpRequests(auth  -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .rememberMe(rememberMe -> rememberMe
                        .tokenValiditySeconds(60)
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(Customizer.withDefaults());

//        2.  원하는 설정으로 바꿔 볼까요?
//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/","/loginForm","/test/*").permitAll() //인증없이 접근 가능한 페이지 설정
//                .anyRequest().authenticated() //나머지 페이지는 모두 인증을 요구하겠다.
//        )
//                .formLogin(formLogin -> formLogin
//                        .loginProcessingUrl("/login_proc")  //기본값은 /login
//                        .loginPage("/loginForm") //해당 url을 여러분이 구현해놔야겠죠?
////                        .defaultSuccessUrl("/info",true)
////                        .failureUrl("/fail")
//                        .usernameParameter("email")
//                        .passwordParameter("passwd")
//                                .successHandler((request,response,authentication)->{
//                                    log.info("로그인 성공!!" + authentication.getName());
//                                    response.sendRedirect("/info");
//                                })
//                                .failureHandler(((request, response, exception) -> {
//                                    log.info("로그인 실패!!" + exception.getMessage());
//                                    response.sendRedirect("/loginForm");
//                                }))
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
////                        로그아웃된 후 추가로 하고싶은일이 있다면..
//                       .addLogoutHandler((request,response,authentication)->{
//                                    log.info("로그아웃 세션,쿠키삭제");
//                                    HttpSession session = request.getSession();
//                                    if(session != null) {
//                                        session.invalidate();
//                                    }
//                                })
//                                .deleteCookies("JSESSIONID") //로그아웃할 때  쿠키 삭제.
//                );
//
//
//        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
