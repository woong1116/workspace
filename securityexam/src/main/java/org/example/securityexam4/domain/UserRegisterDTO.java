package org.example.securityexam4.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private String password;
    private String name;
    private String email;
}
