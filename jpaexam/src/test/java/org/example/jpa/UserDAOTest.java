package org.example.jpa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private static final UserDAO userDAO = new UserDAO();

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("kang");
        user.setEmail("kang@test.com");
    }

    @AfterAll
    static void close() {
        userDAO.close();
    }

    @Test
    @DisplayName("사용자 등록")
    void createUser() {

        userDAO.createUser(user);

        assertNotNull(user.getId());
    }

    @Test
    @DisplayName("사용자 조회")
    void findUser() {

        userDAO.createUser(user);

        User findUser =
                userDAO.findUser(user.getId());

        assertNotNull(findUser);
        assertEquals("kang", findUser.getName());
        assertEquals("kang@test.com", findUser.getEmail());
    }

    @Test
    @DisplayName("사용자 수정")
    void updateUser() {

        userDAO.createUser(user);

        userDAO.updateUser(
                user.getId(),
                "kim",
                "kim@test.com"
        );

        User updatedUser =
                userDAO.findUser(user.getId());

        assertEquals("kim", updatedUser.getName());
        assertEquals("kim@test.com", updatedUser.getEmail());
    }

    @Test
    @DisplayName("사용자 삭제")
    void deleteUser() {

        userDAO.createUser(user);

        Long id = user.getId();

        userDAO.deleteUser(id);

        User deletedUser =
                userDAO.findUser(id);

        assertNull(deletedUser);
    }

}
