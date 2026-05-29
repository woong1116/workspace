package jdbc02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Jdbc02Exam implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Jdbc02Exam.class, args);
    }



    @Autowired
    UserDao userDao;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("kim");
        user.setEmail("kim@gamil.com");

//        userDao.insertUser(user);

        List<User> allUsers = userDao.findAllUsers();
        for (User u : allUsers) {
            System.out.println(u);
        }

        User user1 = userDao.findUserById(2);
        System.out.println(user1);
    }
}
