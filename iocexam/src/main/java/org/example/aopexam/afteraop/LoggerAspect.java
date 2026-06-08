package org.example.aopexam.afteraop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LoggerAspect {

    @Before("execution(* org.example.aopexam..*.*())")
    public void logging(){
//       실제 코드는 좀 더 복잡하게 동작하겠죠?

        System.out.println("\n로그를 남깁니다.");
    }
}
