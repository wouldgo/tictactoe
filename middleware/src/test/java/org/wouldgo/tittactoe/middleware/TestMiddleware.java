package org.wouldgo.tittactoe.middleware;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;
import org.wouldgo.tictactoe.board.BoardFactoryService;
import org.wouldgo.tictactoe.conf.MiddleWareConfiguration;
import org.wouldgo.tictactoe.game.GameFactory;
import org.wouldgo.tictactoe.game.TicTacToeGame;
import org.wouldgo.tictactoe.player.TicTacPlayerFactory;
import org.wouldgo.tictactoe.player.TicTacToePlayer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MiddleWareConfiguration.class)
public class TestMiddleware {

	@Autowired
	private BoardFactoryService boardFactoryService;

	@Autowired
	private GameFactory gameFactory;

	@Autowired
	private TicTacPlayerFactory ticTacPlayerFactory;

	@Test
	public void injectionGoneRigth() {

		Assert.assertNotNull(this.boardFactoryService);
		Assert.assertNotNull(this.gameFactory);
		Assert.assertNotNull(this.ticTacPlayerFactory);
	}

	@Test
	public void createBoard() {

		Board board = this.boardFactoryService.makeBoard();
		Assert.assertNotNull(board);
		Assert.assertTrue(!board.getAvailableStates().isEmpty());
	}

	@Test
	public void createPlayer() {

		TicTacToePlayer aCrossPlayer = this.ticTacPlayerFactory.makeAPlayer(this.boardFactoryService.makeBoard(),
				GameRole.CROSS);
		Assert.assertNotNull(aCrossPlayer);
	}

	@Test
	public void createGame() {

		TicTacToeGame createdGame = this.gameFactory.createGame();
		Assert.assertNotNull(createdGame);
	}
}
