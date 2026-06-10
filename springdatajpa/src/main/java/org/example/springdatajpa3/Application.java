package org.example.springdatajpa3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository){
        return args ->{
//            userRepository.caramiCustom();

            List<User> users = userRepository.findUsersByName("carami");
            for(User u : users){
                System.out.println(u.getName());

            }
        };

    }
}
