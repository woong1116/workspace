package book;

import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthor(String author);
}
