package org.example.friendapp.service;

import lombok.RequiredArgsConstructor;
import org.example.friendapp.domain.Friend;
import org.example.friendapp.repository.FriendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

//    친구 전체 목록을 가져오는 메서드
    @Transactional(readOnly = true)
    public Iterable<Friend> getFriends(){
        return friendRepository.findAll();
    }

//    페이지에 해당하는 목록 가져오는 메서드
    @Transactional(readOnly = true)
    public Page<Friend> getFriends(Pageable pageable){
        return friendRepository.findAll(pageable);
    }

    @Transactional
    public Friend saveFriend(Friend friend){
//        우리는 친구를 등록하기 위한 조건 같은것이 있다면,  여기에서 판단할거예요.
//        조건에 모두 만족 한다면..  친구 정보를 DB 에 저장 할꺼예요.
        return friendRepository.save(friend);
    }

    @Transactional(readOnly = true)
    public Friend getFriend(Long id){
        return friendRepository.findById(id).orElseThrow();
    }
    @Transactional
    public Friend updateFriend(Friend friend){
        return friendRepository.save(friend);
    }

    @Transactional
    public void deleteFriend(Long id){
        friendRepository.deleteById(id);
    }

}
