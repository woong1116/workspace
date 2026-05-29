package org.example.friendapp;

import org.example.friendapp.domain.Friend;
import org.example.friendapp.repository.FriendRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FriendappApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendappApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FriendRepository friendRepository) {
        return args -> {
//            friendRepository.save(new Friend("carami","carami@gmail.com"));
        };
    }

}
