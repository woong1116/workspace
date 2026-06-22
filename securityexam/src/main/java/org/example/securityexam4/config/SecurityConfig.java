package org.example.securityexam4.config;

import lombok.RequiredArgsConstructor;
import org.example.securityexam4.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
//    1. 세션관리자 빈 필요.  (현재 활성 상태인 모든 세션들을 추적하는 역할을 함. )
    @Bean
    public SessionRegistry sessionRegistry(){
        return  new SessionRegistryImpl();
    }
//    2. 세션이 등록될 때 일을 하는 리스너 (이벤트)  세션이 등록되거나 삭제될때 시큐리티에게 알려주는 역할.
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SessionRegistry sessionRegistry) throws Exception{

        http
                .csrf(csrf->csrf.disable());
//        http
//                .authorizeHttpRequests(auth->auth
//                        .anyRequest().permitAll()
//                )
//                .formLogin(Customizer.withDefaults());

        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/login","/register","/","/myinfo").permitAll()
                        .requestMatchers("/welcome").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form->form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/welcome")
                        .permitAll()
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .userDetailsService(customUserDetailsService)
                .sessionManagement(session->session
                        .maximumSessions(1) //동시 접속 허용 개수
                        .maxSessionsPreventsLogin(false) //디폴트 false..  두번째 사용자가 로그인하면 첫번째 사용자가 차단됨.  true - 두번째 사용자 로그인이 안됨.
                        .sessionRegistry(sessionRegistry)
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
