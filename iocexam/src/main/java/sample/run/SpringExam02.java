package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.bean.Dice;
import sample.bean.Game;
import sample.config.GameConfig;

public class SpringExam02 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        Game game = context.getBean(Game.class);
        game.play();

//        Dice dice = context.getBean(Dice.class);
//        System.out.println(dice.getNumber());
    }
}
