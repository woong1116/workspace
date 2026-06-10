package org.example.restexam.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
}
