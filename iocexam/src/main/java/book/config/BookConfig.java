package book.config;

import book.controller.BookController;
import book.repository.BookRepository;
import book.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository());
    }

    @Bean
    public BookController bookController() {
        return new BookController(bookService());
    }
}
