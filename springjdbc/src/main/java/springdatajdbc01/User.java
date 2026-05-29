package springdatajdbc01;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
