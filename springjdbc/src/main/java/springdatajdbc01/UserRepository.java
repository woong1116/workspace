package springdatajdbc01;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
//    이름을 기준으로 조회하고 싶다면?
    List<User> findByName(String name);  //select * from users where name=?
}
