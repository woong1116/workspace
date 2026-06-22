package org.example.securityexam4;

import lombok.extern.slf4j.Slf4j;
import org.example.securityexam4.domain.Role;
import org.example.securityexam4.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Exam4Application {
    public static void main(String[] args) {
        SpringApplication.run(Exam4Application.class);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository){
        return args -> {
          if(roleRepository.count() == 0){
              Role userRole = new Role();
              userRole.setName("USER");
              Role adminRole = new Role();
              adminRole.setName("ADMIN");

              roleRepository.saveAll(List.of(userRole,adminRole));
              log.info("USER,ADMIN 권한이 추가되었습니다." );
          }else{
              log.info("권한정보가 이미 존재합니다.");
          }
        };
    }

}
