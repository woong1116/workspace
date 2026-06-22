package com.example.restapi.repository;

import com.example.restapi.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findAllByOrderByPostedDateDesc(Pageable pageable);
}