package org.example.swaggerexam.service;

import lombok.RequiredArgsConstructor;
import org.example.swaggerexam.entity.User;
import org.example.swaggerexam.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    //    회원가입 (email과 비밀번호를 입력받아서)
    public String register(String email, String password){
        //이메일이 우리 시스템에 이미 존재하는 지 체크
        if(userRepository.findByEmail(email).isPresent()){
//            이미 가입된 사용자라면..
            return "존재하는 아이디 입니다.";
        }
        //DB에 저장
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return "회원가입성공!!";
    }

}
