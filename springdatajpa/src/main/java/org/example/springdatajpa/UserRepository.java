package org.example.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 메서드를 추가하지 않아도 기본 CRUD 사용 가능!

    List<User> findByName(String name);
//
    User findByEmail(String email);
//
//    List<User> findByNameAndEmail(String name, String email);
//
//    List<User> findByNameOrEmail(String name, String email);
//
//    List<User> findByEmailContaining(String expression);

//    @Query("SELECT u FROM User u where u.name = ?1")
//    List<User> getUserName(String name);
//
//    // 기본 Native SQL - 전체 엔티티 조회
//    @Query(value = "SELECT * FROM lion_user WHERE name = :name", nativeQuery = true)
//    List<User> findByNameNative(@Param("name") String name);
//
//    // LIKE 검색
//    @Query(value = "SELECT * FROM lion_user WHERE email LIKE %?1%", nativeQuery = true)
//    List<User> findByEmailNative(String email);
//
//    // 데이터베이스 함수 사용
//    @Query(value = "SELECT * FROM lion_user WHERE YEAR(created_date) = :year",
//            nativeQuery = true)
//    List<User> findUsersByYear(@Param("year") int year);
//}
}