package jdbc02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static java.lang.Math.*;
@Slf4j
//@JdbcTest
//@Import({UserDaoImpl.class})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @SpringBootTest
@Transactional
class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @BeforeEach
    void setUp() {
//        테스트메서드가 실행되기전 초기화 해야햘 일이 있다면 여기 구현..
        log.info("@BeforeEach UserDaoImplTest");
        Math.random();
        random();
        abs(random());

//        UserDao dao = new UserDaoImpl();


    }

    @AfterEach
    void tearDown() {
//        테스트가 종료된 후에 정리해야 할 것이 있다면 여기에 구현!!
        log.info("@AfterEach UserDaoImplTest");
    }

    @Test
    @DisplayName("사용자를 추가할 수 있다.")
    void insertUser() {
        log.info("insertUser test 실행");
        User user = new User();
        user.setName("aaa");
        user.setEmail("carami@gamilc.om");
        int count = userDao.insertUser(user);
        
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("전체 사용자를 조회 할 수 있다.")
    void findAllUsers() {
        log.info("findAllUsers test 실행");
        List<User> users = userDao.findAllUsers();
        assertThat(users).hasSize(5);
        assertThat(users.get(0).getName()).isEqualTo("carami");
    }

    @Test
    void findUserById() {
        log.info("findUserById test 실행");
        User user = userDao.findUserById(10);
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("carami");
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("carami2");
        user.setEmail("carami2@gmail.com");

        userDao.updateUser(user);
        User updateUser = userDao.findUserById(1);

        assertThat(updateUser.getName()).isEqualTo("carami2");
        assertThat(updateUser.getEmail()).isEqualTo("carami2@gmail.com");
    }

    @Test
    void deleteUser() {
        userDao.deleteUser(1);
//        User updateUser = userDao.findUserById(1);
//        assertThat(updateUser).isNull();

//        assertThat(userDao.findUserById(1)).isNull();

        assertThatThrownBy(() -> userDao.findUserById(1)).isInstanceOf(EmptyResultDataAccessException.class);

    }
}