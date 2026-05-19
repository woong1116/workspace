package org.example.iocexam.dao;

import org.example.iocexam.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("caramiDao")
public class UserCaramiDaoImpl implements UserDao{
    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void addUser(User user) {
        System.out.println(user.getName() + "정보가 carami dao를 통해서 저장되었습니다.");
    }
}
