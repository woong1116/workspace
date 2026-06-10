package org.example.springdatajpa2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner run(CustomerRepository repository, CustomerService customerService){
        return args -> {
//            Customer customer = new Customer("carami", "carami@gmail.com");
//            repository.save(customer);
//고객정보와 주문수 출력!!
            List<Object[]> customerOrderCount = repository.findCustomerOrderCount();
            for(Object[] result: customerOrderCount){
                Customer customer = (Customer) result[0];
                Long count = (Long) result[1];
                System.out.println("고객: " + customer.getName() + ", 주문수: " + count);
            }

            // 고객 정보와 최근 주문 출력
            List<Object[]> latestOrders = repository.findCustomersWithLatestOrder();
            for (Object[] result : latestOrders) {
                Customer customer = (Customer) result[0];
                Order order = (Order) result[1];
                System.out.println("고객: " + customer.getName() + ", 최근 주문 날짜: " + order.getDate());
            }

            //평균나이보다 많은 고객 정보 출력
            List<Customer> olderThanAverage = repository.findCustomersOlderThanAverage();
            for(Customer customer : olderThanAverage){
                System.out.println("평균 나이보다 많은 고객: " + customer.getName() + ", 나이: " + customer.getAge());
            }

        };
    }
}
