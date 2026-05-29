package org.example.friendapp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("friends")
public class Friend {
    @Id
    private Long id;
    private String name;
    private String email;

    public Friend(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
