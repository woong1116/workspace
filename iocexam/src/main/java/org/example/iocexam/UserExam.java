package org.example.iocexam;

import org.example.iocexam.config.UserConfig;
import org.example.iocexam.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.Dice;

public class UserExam {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);

        UserController controller = context.getBean(UserController.class);
        controller.joinUser();
// 실행하면,  carami 의 정보가 저장되었습니다.   라고 출력되면 성공!!!

        Dice dice = context.getBean(Dice.class);
        System.out.println(dice.getNumber());

    }
}
