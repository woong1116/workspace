package org.example.springdatajpa2;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //    이름으로 조회하는 기능
    List<Customer> findByName(String name);
    //    이메일로 조회
    Optional<Customer> findByEmail(String email);

    //    이메일에 특정 문자열이 포함된 고객 조회
    List<Customer> findByEmailContaining(String email);
    List<Customer> findByEmailContaining(String email, Pageable pageable);

    //    각 고객과 고객의 주문수를 알고 싶다면?
    @Query("SELECT c, count(o) FROM Customer c LEFT JOIN c.orders o GROUP BY c")
    List<Object[]> findCustomerOrderCount();

    //    평균나이보다 나이가 많은 고객 정보를 구하고 싶다면??
    @Query("SELECT c FROM Customer c WHERE c.age > (SELECT avg(c2.age) FROM Customer c2)")
    List<Customer> findCustomersOlderThanAverage();

    //    고객정보와 그 고객이 가장 최근 주문을 조회하고 싶다면?
    @Query("SELECT c, o FROM Customer c join c.orders o WHERE o.date = (SELECT MAX(o2.date) FROM Order o2 where o2.customer = c)")
    List<Object[]> findCustomersWithLatestOrder();


}