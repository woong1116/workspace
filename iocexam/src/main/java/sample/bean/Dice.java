package sample.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Dice {
    private int face=6;
    public Dice(){
        System.out.println("Dice() 생성");
    }

    public Dice(int face){
        this.face = face;
        System.out.println("Dice(int) 실행!!");
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getNumber(){
        return (int)(Math.random()*face)+1;
    }

    @PostConstruct
    public void init(){
        System.out.println("Dice 가 생성된 직 후에 호출됨!!!");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("객체가 소멸되기 전에 호출됨!!!");
    }
}
