package org.example.aopexam.beforeaop;

public class Main {
    public static void main(String[] args) {
        UserDao dao = new UserDao();
        dao.addUser();
    }
}
