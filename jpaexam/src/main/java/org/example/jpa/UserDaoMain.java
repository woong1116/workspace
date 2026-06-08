package org.example.jpa;

public class UserDaoMain {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

//        dao.createUser(new User("kkk","kkk@kkk.com"));

        User user = dao.findUser(1L);
        System.out.println(user);
    }
}
