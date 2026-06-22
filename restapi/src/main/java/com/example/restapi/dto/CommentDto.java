package com.example.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Integer postId;
    private String content;
}