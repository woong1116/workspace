package org.example.iocexam.dao;

import org.example.iocexam.domain.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
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
        System.out.println(user.getName()+"의 정보가 저장되었습니다.");
    }
}
