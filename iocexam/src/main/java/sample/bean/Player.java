package sample.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Player {
    private String name;
    private Dice dice;   //실행될 때 주사위를 주입 (DI)

    public Player() {

    }
    public Player(String name, Dice dice) {
        this.name = name;
        this.dice = dice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void play(){
        System.out.println(name + "은 주사위를 던져서 "+dice.getNumber() + "가 나왔습니다.");
    }
}
