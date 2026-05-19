package sample.run;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.Dice;
import sample.bean.MyBean;
import sample.config.BeanConfig;

public class SpringExam04 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        MyBean myBean = applicationContext.getBean(MyBean.class);
        Dice dice = applicationContext.getBean(Dice.class);
        int number = dice.getNumber();
        System.out.println(number);

    }
}
