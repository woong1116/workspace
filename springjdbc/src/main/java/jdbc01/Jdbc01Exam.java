package jdbc01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class Jdbc01Exam implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Jdbc01Exam.class, args);
    }



    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // 나는 프레임워크에게 알려줘야 할 것은 무엇일까??
//        String sql = "insert into users(name,email) values(?,?)";
//        int resultCount = jdbcTemplate.update(sql, "kang", "kang@gamilc.om");
//        System.out.println(resultCount);


//        조회 - 쿼리, 값을 여기에 담아주세요.(RowMapper)
        String sql2 = "select id,name,email,created_at from users";
        List<User> users = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(User.class));

        for (User user : users) {
            System.out.println(user);
        }

        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(rs.getLong("id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return user;
            }
        };

        List<User> users2 = jdbcTemplate.query(sql2, rowMapper);

        for (User user : users2) {
            System.out.println(user);
        }
    }
}
