package org.example.springdatajpa2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;
    //    고객추가
    @Transactional
    public Customer createCustomer(String name, String email, int age) {
        Customer customer = new Customer(name, email);
        customer.setAge(age);
        return customerRepository.save(customer);
    }
    //    고객정보 조회 (id)
    public Customer findCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("고객을 찾을 수 없습니다"));
    }

    //    고객정보 전체조회
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    //    고객정보수정
    @Transactional
    public Customer updateCustomer(Long id, String name, String email, int age) {
        Customer customer = findCustomer(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setAge(age);
        return customer;
    }
    //    고객정보 삭제
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = findCustomer(id);
        customerRepository.delete(customer);
    }

    //    고객정보조회 (email)
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 고객이 없습니다"));
    }

    //    // Customer와 그에 해당하는 모든 Order를 조회하는 메서드 예제
    @Transactional
    public void customersAndOrders() {
        List<Customer> customers = customerRepository.findAll(); // 모든 고객을 조회합니다.

        for (Customer customer : customers) {
            List<Order> orders = customer.getOrders(); // 각 고객이 가진 주문들을 조회합니다.
            System.out.println("Customer: " + customer.getName());
            for (Order order : orders) {
                System.out.println("Order: " + order.getProduct() + ", Date: " + order.getDate());
            }
        }
    }


}
