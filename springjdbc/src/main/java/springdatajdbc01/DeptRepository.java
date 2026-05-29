package springdatajdbc02;

import org.springframework.data.repository.CrudRepository;
import springdatajdbc01.Dept;

import java.util.List;

public interface DeptRepository extends CrudRepository(Dept, Int) {

    Dept findByDname(String dname);
}
