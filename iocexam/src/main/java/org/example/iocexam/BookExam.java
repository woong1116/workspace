package org.example.iocexam;

import org.example.iocexam.book.BookController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookExam {
    public static void main(String[] args) {
        SpringApplication.run(BookExam.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BookController bookController) {
        return (args) -> {
            bookController.test();

        };
    }
}
