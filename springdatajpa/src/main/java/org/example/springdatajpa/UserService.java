package org.example.springdatajpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        User updateuser = userRepository.findById(user.getId()).
                orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        updateuser.setName(user.getName());
        updateuser.setEmail(user.getEmail());

        return updateuser;
    }



//    public User deleteUser() {
//        return userRepository.delete();
//    }
//
//    public User findAllUser(User user) {
//        return (User) userRepository.findAll();
//    }
//
//    public User findUser(User user) {
//        return userRepository.findById(1L);
//    }
}
