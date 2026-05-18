package sample.bean;

import java.util.List;

public class Game {
    private List<Player> list; //주입(DI) Player List 를 스프링 공장이 생성해서 주입해줌.

    public Game(List<Player> list){
        this.list = list;
    }
//    public void setList(List<Player> list) {
//        this.list = list;
//    }

    public void play(){
        for(Player player : list){
            player.play();
        }
    }
}
