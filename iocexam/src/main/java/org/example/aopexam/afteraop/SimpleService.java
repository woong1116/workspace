package org.example.aopexam.afteraop;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    public String doSomething(){
        return "Doing something...";
    }


//    public String doSomething2(){
//        System.out.println("SimpleService2의 doSomething run!!");
//
//
//
////        if(1==1)
////            throw new RuntimeException();
//        return "carami";
//    }
//
//    public void hello(){
//        System.out.println("hello 실행!!");
//    }
}
