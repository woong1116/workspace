package org.example.board.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("board")
public class Board {
    @Id
    private Long id;
    private String name;
    private String title;
    private String password;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedDate
    private LocalDateTime updatedAt;

    public Board(String name, String title, String password, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.title = title;
        this.password = password;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
