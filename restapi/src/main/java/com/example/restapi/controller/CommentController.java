package com.example.restapi.controller;

import com.example.restapi.domain.Comment;
import com.example.restapi.domain.User;
import com.example.restapi.dto.CommentDto;
import com.example.restapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal User loginUser) {

        Comment comment = commentService.createComment(commentDto, loginUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(
            @RequestParam Integer postId) {

        List<Comment> comments = commentService.getCommentsByPostId(postId);

        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer id,
            @AuthenticationPrincipal User loginUser) {

        commentService.softDeleteComment(id, loginUser);

        return ResponseEntity.noContent().build();
    }
}