package book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("books")
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publishYear;
    private Boolean available;  // 대여 가능 여부
    private LocalDateTime createdAt;

    public Book(String title, String author, String isbn, Integer publishYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.available = true;  // 기본값: 대여 가능
    }
}