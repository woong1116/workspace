package org.example.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 메서드를 추가하지 않아도 기본 CRUD 사용 가능!
}