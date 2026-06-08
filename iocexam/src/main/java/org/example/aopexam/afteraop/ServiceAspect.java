package org.example.aopexam.afteraop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ServiceAspect {

//    @Pointcut("execution(* org.example..SimpleService.*())")
//    public void pc(){}
//
//    @Pointcut("execution(String org.example..*Service.*())")
//    public void pc2(){}
//
//    @Pointcut("execution(* org.example..SimpleService.hello())")
//    public void helloPc(){}
//
//    @Before("pc()")
//    public void before(JoinPoint joinPoint){
//        System.out.println("Before========= 메서드가 실행되기 전에 실행되는 advice::: "+joinPoint.getSignature().getName());
//    }
//
//    @After("pc()")
//    public void after(JoinPoint joinPoint){
//        System.out.println("After========= 메서드가 실행된 후 실행되는 advice::: "+joinPoint.getSignature().getName());
//    }
//
//    @AfterReturning(pointcut = "pc2()", returning = "result")
//    public void AfterRetrurning(String result){
//        System.out.println("AfterReturning========= 메서드가 실행된 후 실행되는 advice::: ");
//        System.out.println("AfterReturning========= result::: "+result);
//    }
//
//    @AfterThrowing(pointcut = "pc()", throwing = "ex")
//    public void afterThrowing(Exception ex){
//        System.out.println("AfterThrowing =====  Exception :::"+ ex.getMessage());
//    }
//
//    @Around("pc2()")
//    public String around(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("Around ===== Target 이 실행되기 전에 해야할일은 여기 구현" + pjp.getSignature().getName());
//
//        String returnValue = (String) pjp.proceed();    // 이 메서드가 실제 Target 호출해줌!!
//
//        System.out.println("Around ===== Target 이 실행된 후 해야할일은 여기 구현" + pjp.getSignature().getName());
////        returnValue를 조작할 수 있어요.
//        returnValue += " hello~~!!";
//
//
//        return returnValue;
//    }
@Before("execution(* org.example.aopexam.afteraop.*.*(..))")
public void beforeServiceMethod(JoinPoint joinPoint) {
    System.out.println("Before method: " + joinPoint.getSignature().getName());
}

    @AfterReturning(
            pointcut = "execution(* org.example.aopexam.afteraop.*.*(..))",
            returning = "result"
    )
    public void afterReturningServiceMethod(JoinPoint joinPoint, Object result) {
        System.out.println("After method: " + joinPoint.getSignature().getName() +
                ", return value: " + result);
    }
}
