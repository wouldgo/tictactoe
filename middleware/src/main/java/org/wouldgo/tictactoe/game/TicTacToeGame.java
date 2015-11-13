package org.wouldgo.tictactoe.game;

import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;
import org.wouldgo.tictactoe.dto.GameStatus;
import org.wouldgo.tictactoe.player.TicTacToePlayer;

public final class TicTacToeGame {
	private final TicTacToePlayer firstPlayer, secondPlayer;
	private final Board gameBoard;
	private GameRole lastMove = GameRole.CROSS;

	public TicTacToeGame(TicTacToePlayer crossPlayer, TicTacToePlayer knotPlayer, Board gameBoard) {

		if (crossPlayer != null && knotPlayer != null && gameBoard != null) {

			this.firstPlayer = crossPlayer;
			this.secondPlayer = knotPlayer;
			this.gameBoard = gameBoard;
		} else {

			throw new IllegalArgumentException("Game parameters are invalid");
		}
	}

	public final GameStatus makeMove() {

		if (!gameBoard.isGameOver()) {

			if (lastMove == GameRole.CROSS) {

				firstPlayer.move();
			} else {

				secondPlayer.move();
			}
		}

		if (gameBoard.hasWon(GameRole.CROSS)) {

			return GameStatus.CROSSWIN;
		} else if (gameBoard.hasWon(GameRole.KNOT)) {

			return GameStatus.KNOTWIN;
		} else if (gameBoard.getAvailableStates().isEmpty()) {

			return GameStatus.DRAW;
		} else {

			return GameStatus.NOTFINISHED;
		}
	}
}
