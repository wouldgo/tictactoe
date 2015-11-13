package org.wouldgo.tictactoe.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;

@Component
final class TicTacPlayerFactoryImpl implements TicTacPlayerFactory {

	private static final transient Logger logger = LoggerFactory.getLogger(TicTacPlayerFactoryImpl.class);

	@Override
	public TicTacToePlayer makeAPlayer(Board board, GameRole role) {
		if (logger.isInfoEnabled()) {

			logger.info("Generating a player with {1}, {2}", board, role);
		}

		return new MinMaxPlayer(board, role);
	}
}
