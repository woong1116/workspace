package org.example.iocexam.service;

import jakarta.annotation.Resource;
import org.example.iocexam.dao.UserDao;
import org.example.iocexam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class UserServiceImpl implements UserService {
//    필드를 통한 주입은 권장하지 않는다!!  (스프링에 종속적이니까!!)
//    @Autowired
//    @Qualifier("caramiDao")
    private UserDao userDao;

    public UserServiceImpl() {
        System.out.println("UserServiceImpl() 생성자 호출!!");
    }

//    public UserServiceImpl(@Qualifier("caramiDao") UserDao userDao){
//        this.userDao = userDao;
//    }

    @Resource
    public void setUserDao(@Qualifier("caramiDao")  UserDao userDao) {
        System.out.println("setUserDao() 실행!!!");
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
