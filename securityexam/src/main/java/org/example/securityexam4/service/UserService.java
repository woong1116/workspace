package org.example.securityexam4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.securityexam4.domain.Role;
import org.example.securityexam4.domain.User;
import org.example.securityexam4.domain.UserRegisterDTO;
import org.example.securityexam4.repository.RoleRepository;
import org.example.securityexam4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

//    회원가입 -- 반드시 비밀번호를 인코딩해서 입력해주세요!!!  권한!!
    @Transactional
    public User joinUser(UserRegisterDTO userDto){

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));  //반드시 패스워드는 인코딩해야함!!!
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        //        롤에 대한 값을 채워줘야 할꺼예요.
        Role userRole = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

//    username에 해당하는 user가 있는지 체크하는 기능
    public boolean existsUser(String username){
        return userRepository.existsByUsername(username);
    }
}
