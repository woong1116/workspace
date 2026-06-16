package org.example.securityexam3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/shop/**","/static/**","/home","/userinfo").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/admin/abc").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("SUPERUSER")
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/home/hello",true)
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/hello")
                );



        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        USER,ADMIN, SUPERUSER --> 이렇게 3개의 롤을 이용해봅시다.
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("carami")
                .password(passwordEncoder.encode("1234"))
                .roles("USER","ADMIN")
                .build();
        UserDetails user3 = User.withUsername("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("ADMIN")
                .build();
        UserDetails user4 = User.withUsername("superuser")
                .password(passwordEncoder.encode("1234"))
                .roles("SUPERUSER")
                .build();


        return new InMemoryUserDetailsManager(user,user2,user3,user4);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
