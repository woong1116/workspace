package com.example.restapi.service;

import com.example.restapi.domain.Post;
import com.example.restapi.domain.User;
import com.example.restapi.dto.PostDto;
import com.example.restapi.exception.PostNotFoundException;
import com.example.restapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(PostDto postDto, User loginUser) {

        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .user(loginUser) // 작성자 자동 세팅
                .build();

        return postRepository.save(post);
    }

    public Post updatePost(Integer id, PostDto postDto, User loginUser) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글이 존재하지 않습니다."));

        if (!post.getUser().getId().equals(loginUser.getId())) {
            throw new SecurityException("수정 권한이 없습니다.");
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return postRepository.save(post);
    }

    public void deletePost(Integer id, User loginUser) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글이 존재하지 않습니다."));

        if (!post.getUser().getId().equals(loginUser.getId())) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }

        postRepository.delete(post);
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAllByOrderByPostedDateDesc(pageable);
    }

    public Post getPost(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글이 존재하지 않습니다."));
    }
}