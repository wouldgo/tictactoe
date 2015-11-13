package org.wouldgo.tictactoe.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;
import org.wouldgo.tictactoe.board.BoardFactoryService;
import org.wouldgo.tictactoe.player.TicTacPlayerFactory;

@Component
class GameFactoryImpl implements GameFactory {

	private static final transient Logger logger = LoggerFactory.getLogger(GameFactoryImpl.class);

	@Autowired
	private BoardFactoryService boardFactoryService;

	@Autowired
	private TicTacPlayerFactory ticTacPlayerFactory;

	@Override
	public TicTacToeGame createGame() {

		if (logger.isInfoEnabled()) {

			logger.info("Creating a game...");
		}
		Board newBoard = boardFactoryService.makeBoard();
		return new TicTacToeGame(ticTacPlayerFactory.makeAPlayer(newBoard, GameRole.CROSS),
				ticTacPlayerFactory.makeAPlayer(newBoard, GameRole.KNOT), newBoard);
	}
}
