package org.wouldgo.tictactoe.player;

import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;

/**
 * Abstract superclass for all players
 */
public abstract class TicTacToePlayer {

	protected final Board board;
	protected final GameRole myRole, hisRole;

	public TicTacToePlayer(Board board, GameRole role) {
		this.board = board;
		this.myRole = role;
		this.hisRole = role == GameRole.CROSS ? GameRole.KNOT : GameRole.CROSS;
	}

	public abstract void move();
}
