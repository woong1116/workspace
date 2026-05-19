package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;

import java.util.List;

@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public Dice dice(){
        return new Dice(6);
    }

    @Bean
    public Player kang(Dice dice){
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
