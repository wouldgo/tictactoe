package org.wouldgo.tictactoe.player;

import org.springframework.stereotype.Service;
import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;

@Service
public interface TicTacPlayerFactory {

	public TicTacToePlayer makeAPlayer(Board board, GameRole role);
}
