package book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springdatajdbc01.UserRepository;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(book.Application.class, args);}

        @Bean
        CommandLineRunner commandLineRunner(BookRepository bookRepository) {
            return args ->{
                bookRepository.findByAuthor("woong").forEach(System.out::println);
            }
    }
}
