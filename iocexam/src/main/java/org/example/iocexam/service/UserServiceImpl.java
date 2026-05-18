package org.example.iocexam.service;

import org.example.iocexam.dao.UserDao;
import org.example.iocexam.domain.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public void joinUser(User user) {
        //회원가입을 위한 비지니스로직 실행!!
//        회원 가입에 요건이 모두 만족 한다면 회원 가입.
//        회원의 정보를 저장할 필요가 있을 거예요.

        userDao.addUser(user);
    }
}
