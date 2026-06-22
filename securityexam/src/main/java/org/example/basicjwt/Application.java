package org.example.basicjwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner run(JwtTokenizer jwtTokenizer){
        return args -> {
            String accessToken = jwtTokenizer.createAccessToken(
                    1L,
                    "carami@gmail.com",
                    "강경미",
                    "carami",
                    Arrays.asList("ROLE_ADMIN", "ROLE_USER")
            );

            log.info("accessToken :: "+ accessToken);

            String refreshToken = jwtTokenizer.createRefreshToken(
                    1L,
                    "carami@gmail.com",
                    "강경미",
                    "carami",
                    Arrays.asList("ROLE_ADMIN", "ROLE_USER")
            );

            log.info("accessToken :: " + accessToken);


        };

    }
}
