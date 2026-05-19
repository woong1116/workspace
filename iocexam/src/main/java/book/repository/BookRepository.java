package book.repository;

import book.bean.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    public Book save(Book book) {
        return new Book();
    }

    public List<Book> findAll() {
        return new ArrayList<>();
    }

    public Book findById() {
        return new Book();
    }

    public void deleteById() {
        return;
    }
}
