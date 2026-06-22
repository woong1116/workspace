package com.example.restapi.service;

import com.example.restapi.domain.Comment;
import com.example.restapi.domain.Post;
import com.example.restapi.domain.User;
import com.example.restapi.dto.CommentDto;
import com.example.restapi.exception.CommentNotFoundException;
import com.example.restapi.repository.CommentRepository;
import com.example.restapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Comment createComment(CommentDto commentDto, User loginUser) {

        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new CommentNotFoundException("게시글이 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .post(post)
                .user(loginUser)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CommentNotFoundException("게시글이 존재하지 않습니다."));

        return commentRepository.findByPostAndDelYnFalseOrderByRegDateAsc(post);
    }

    public void softDeleteComment(Integer id, User loginUser) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("댓글이 존재하지 않습니다."));

        if (!comment.getUser().getId().equals(loginUser.getId())) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }

        comment.delete();

        commentRepository.save(comment);
    }
}