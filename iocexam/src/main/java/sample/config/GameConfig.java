package sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;

import java.util.List;

@PropertySource({"classpath:dice.properties"})
public class GameConfig {
    @Value("${face}")
    int face;
//    Dice, Player, Game  Bean 등록 해볼까요??
    @Bean
    public Dice dice(){
        return new Dice(face);
    }

//    @Bean
//    public Player kang(){
//        Player player = new Player();
//        player.setName("Kang");
//        player.setDice(dice());
//      return player;
//    }

    @Bean
    public Player kang(Dice dice){
//        Player player = new Player();
//        player.setName("Kang");
//        player.setDice(dice);
//        return player;
        return new Player("kang",dice);
    }

    @Bean
    public Player ann(Dice dice){
        Player player = new Player();
        player.setName("ann");
        player.setDice(dice);
        return player;
    }

    @Bean
    public Player hong(Dice dice){
        Player player = new Player();
        player.setName("hong");
        player.setDice(dice);
        return player;
    }

    @Bean
    public Player kim(Dice dice){
        Player player = new Player();
        player.setName("kim");
        player.setDice(dice);
        return player;
    }

    @Bean
    public Game game(List<Player> players){
//        Game game = new Game();
//        game.setList(players);
//        return game;
        return new Game(players);
    }

}
