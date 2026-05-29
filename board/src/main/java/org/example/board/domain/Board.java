package org.example.board.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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

    public Board(String name, String title, String password, String content) {
        this.name = name;
        this.title = title;
        this.password = password;
        this.content = content;
    }
}
