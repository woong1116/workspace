package com.example.restapi.controller;

import com.example.restapi.domain.Post;
import com.example.restapi.domain.User;
import com.example.restapi.dto.PostDto;
import com.example.restapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto, @AuthenticationPrincipal User loginUser) {

        Post post = postService.createPost(postDto, loginUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody PostDto postDto, @AuthenticationPrincipal User loginUser) {

        Post post = postService.updatePost(id, postDto, loginUser);

        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id, @AuthenticationPrincipal User loginUser) {

        postService.deletePost(id, loginUser);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Post> posts = postService.getPosts(PageRequest.of(page, size));

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Integer id) {

        Post post = postService.getPost(id);

        return ResponseEntity.ok(post);
    }
}