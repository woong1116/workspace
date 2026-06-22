package com.example.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String name;
    private String loginId;
    private String password;
    private String email;
}