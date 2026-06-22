package com.example.restapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true,length = 100)
    private String name;
    @Column(nullable = false, length = 100)
    private String loginId;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(name = "joined_date", nullable = false, updatable = false)
    private LocalDateTime joinedDate = LocalDateTime.now();

}
