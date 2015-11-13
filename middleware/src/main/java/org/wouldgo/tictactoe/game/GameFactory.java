package org.wouldgo.tictactoe.game;

import org.springframework.stereotype.Service;

@Service
public interface GameFactory {

	public TicTacToeGame createGame();
}
