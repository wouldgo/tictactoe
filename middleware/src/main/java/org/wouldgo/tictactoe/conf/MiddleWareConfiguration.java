package org.wouldgo.tictactoe.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.wouldgo.tictactoe.board.BoardFactoryService;
import org.wouldgo.tictactoe.game.GameFactory;
import org.wouldgo.tictactoe.game.GamesState;
import org.wouldgo.tictactoe.player.TicTacPlayerFactory;

@Configuration
@ComponentScan(basePackageClasses = { BoardFactoryService.class, GameFactory.class, TicTacPlayerFactory.class })
public class MiddleWareConfiguration {

	@Bean
	public static GamesState gameState() {

		return new GamesState();
	}
}
