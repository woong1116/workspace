package org.example.iocexam.book;

import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void test(){
        bookService.test();
    }
}
