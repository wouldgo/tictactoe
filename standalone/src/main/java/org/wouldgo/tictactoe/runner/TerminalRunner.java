package org.wouldgo.tictactoe.runner;

import static java.lang.System.out;

import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;
import org.wouldgo.tictactoe.player.MinMaxPlayer;
import org.wouldgo.tictactoe.player.TicTacToePlayer;

public final class TerminalRunner {

	private static int twoSecs = 2000;

	private static void dumpAndWait(Board aBoard) throws InterruptedException {

		out.println("--------------------");
		out.println(aBoard);
		Thread.sleep(twoSecs);
	}

	public static void main(String[] args) throws InterruptedException {

		Board aBoard = new Board();
		TicTacToePlayer firstPlayer = new MinMaxPlayer(aBoard, GameRole.CROSS),
				secondPlayer = new MinMaxPlayer(aBoard, GameRole.KNOT);

		while (!aBoard.isGameOver()) {

			firstPlayer.move();
			dumpAndWait(aBoard);
			if (!aBoard.isGameOver()) {

				secondPlayer.move();
				dumpAndWait(aBoard);
			}
		}

		if (aBoard.hasWon(GameRole.CROSS)) {

			out.println("Cross wins\r\n");
		} else if (aBoard.hasWon(GameRole.KNOT)) {

			out.println("Knot wins\r\n");
		} else {

			out.print("Draw\r\n");
		}
	}
}
