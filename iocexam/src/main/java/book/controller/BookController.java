package book.controller;

import book.bean.Book;
import book.service.BookService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book addBook(Book book) {
        return bookService.addBook(book);
    }

    public void deleteBook() {
        bookService.deleteBook();
    }
}