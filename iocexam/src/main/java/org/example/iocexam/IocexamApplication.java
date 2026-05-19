package org.example.iocexam;

import org.example.iocexam.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sample.bean.Dice;
import sample.bean.Game;

@SpringBootApplication
public class IocexamApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IocexamApplication.class, args);
    }

//    @Autowired
//    private UserController userController;
//    @Autowired
//    private Dice dice;

    @Autowired
    private Game game;
    @Override
    public void run(String... args) throws Exception {
//        userController.joinUser();
//
//        System.out.println(dice.getNumber());

        game.play();
    }
}
