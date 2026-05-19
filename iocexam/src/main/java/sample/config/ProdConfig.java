package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;

import java.util.List;

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public Dice dice(){
        return new Dice(6);
    }

    @Bean
    public Player kang(Dice dice){
        return new Player("강경미",dice);
    }

    @Bean
    public Player kang2(Dice dice){
        return new Player("강아무개",dice);
    }

    @Bean
    public Player ann(Dice dice){
        Player player = new Player();
        player.setName("안다영");
        player.setDice(dice);
        return player;
    }

    @Bean
    public Player hong(Dice dice){
        Player player = new Player();
        player.setName("홍길동");
        player.setDice(dice);
        return player;
    }

    @Bean
    public Player kim(Dice dice){
        Player player = new Player();
        player.setName("김수안");
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
