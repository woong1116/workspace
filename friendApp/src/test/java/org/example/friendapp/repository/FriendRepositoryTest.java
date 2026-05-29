package org.example.friendapp.repository;

import org.example.friendapp.domain.Friend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Transactional
class FriendRepositoryTest {
    @Autowired
    private FriendRepository friendRepository;

    @Test
    void save(){
        Friend friend = new Friend("둘리","dul@gmail.com");
        Friend savedFriend = friendRepository.save(friend);

        assertThat(savedFriend.getId()).isNotNull();
    }

    @Test
    void paging(){
        friendRepository.findAll(PageRequest.of(0, 2)).forEach(System.out::println);
    }

}