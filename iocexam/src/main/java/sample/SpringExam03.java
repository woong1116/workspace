package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sample.bean.Dice;
import sample.bean.MyBean;

@SpringBootApplication
public class SpringExam03 implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringExam03.class, args);
    }

    @Autowired
    private Dice dice;
    @Autowired
    private MyBean myBean;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(dice.getNumber());
        myBean.setName("carami");
        System.out.println(myBean.getName());
    }
}
