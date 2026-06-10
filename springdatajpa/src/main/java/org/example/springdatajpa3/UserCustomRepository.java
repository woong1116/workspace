package org.example.springdatajpa3;

import java.util.List;

public interface UserCustomRepository {
    void caramiCustom();
    List<User> findUsersByName(String name);

    List<User> findUsersDynamically(String name, String email);
}
