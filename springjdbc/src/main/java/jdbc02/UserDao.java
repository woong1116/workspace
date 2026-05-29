package jdbc02;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    List<User> findAllUsers();
    User findUserById(int id);
    void updateUser(User user);
    void deleteUser(int id);
}
