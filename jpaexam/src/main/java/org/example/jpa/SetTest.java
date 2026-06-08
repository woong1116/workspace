package org.example.jpa;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        User user1 = new User("Alice", "alice@example.com");  // id = null
        User user2 = new User("Alice", "alice@example.com");


        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        for(User user : users){
            System.out.println(user);
        }
    }
}
