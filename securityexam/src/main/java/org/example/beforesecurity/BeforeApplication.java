package org.example.beforesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BeforeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeforeApplication.class);
    }
}
