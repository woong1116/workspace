package sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import sample.bean.Book;
import sample.bean.Dice;
import sample.bean.MyBean;

@PropertySource({"classpath:dice.properties"})
public class MyBeanConfig {
//    스프링 공장에게 나 어떤 빈을 관리할지 알려주는 파일.
//    xml
//    <bean id="myBean" class="sample.bean.Mybean"></bean>
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    @Bean
    public MyBean myBean2() {
        return new MyBean();
    }

    @Bean
    @Scope("prototype")
    public MyBean myBean3() {
        return new MyBean();
    }

    @Bean
    public MyBean myBean4() {
        MyBean myBean = new MyBean();
        myBean.setName("kang");
        myBean.setCount(1);
        return myBean;
    }


    @Bean
    public Book book(){
        return new Book();
    }

    @Bean
    public Dice dice(){
        return new Dice();
    }

    @Bean
    public Dice dice2(){
        return new Dice(45);
    }

    @Bean
    public Dice dice3(@Value("${face}")int face){
//        생성자를 통해서 face 값을 넣어주는 경우.
        return new Dice(face);
    }

    @Bean
    public Dice dice4(@Value("${face}")int face){
        Dice dice = new Dice();
        dice.setFace(face);
        return dice;
    }
}
