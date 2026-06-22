package com.example.restapi.service;

import com.example.restapi.domain.User;
import com.example.restapi.dto.UserDto;
import com.example.restapi.exception.DuplicateUserException;
import com.example.restapi.exception.UserNotFoundException;
import com.example.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) {

        if (userRepository.existsByLoginId(userDto.getLoginId())) {
            throw new DuplicateUserException("이미 존재하는 Id입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = User.builder()
                .name(userDto.getName())
                .loginId(userDto.getLoginId())
                .password(encodedPassword)
                .email(userDto.getEmail())
                .build();

        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {

        User user = userRepository.findByLoginId("삭제")
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다."));

        userRepository.delete(user);
    }
}