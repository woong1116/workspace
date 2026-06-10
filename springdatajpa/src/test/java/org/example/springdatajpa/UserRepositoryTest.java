package org.example.springdatajpa;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
//@SpringBootTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws Exception {
        userRepository.deleteAll();

        userRepository.save(new User("carami","carami@gmail.com"));
        userRepository.save(new User("carami","carami2@gmail.com"));
        userRepository.save(new User("kang","kang@gmail.com"));
        userRepository.save(new User("kim","kim@gmail.com"));
        userRepository.save(new User("hong","hong@gmail.com"));
        userRepository.save(new User("lee","lee@gmail.com"));
    }

    @Test
    void findByName(){
        List<User> users = userRepository.findByName("carami");

        assertThat(users).hasSize(2);

    }

}