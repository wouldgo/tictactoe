package org.wouldgo.tictactoe.player;

import static java.lang.System.out;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;

public class TestMinMaxPlayer {

	private static Board testingBoard;

	@Before
	public final void beforeEach() {

		testingBoard = new Board();
	}

	@Test
	public void scenarioOneBoardCreated() {

		Assert.assertNotNull(testingBoard);
		out.println("scenario 1 - board created");
		out.println(testingBoard);
	}

	@Test
	public void scenarioTwoPlayerXWinWithVerticalLine() {

		Assert.assertNotNull(testingBoard);
		TicTacToePlayer player = new MinMaxPlayer(testingBoard, GameRole.CROSS);
		testingBoard.setCellAt(0, 0, GameRole.CROSS);
		testingBoard.setCellAt(1, 0, GameRole.CROSS);
		testingBoard.setCellAt(1, 1, GameRole.KNOT);
		testingBoard.setCellAt(2, 2, GameRole.KNOT);
		player.move();

		Assert.assertTrue(testingBoard.getRoleAt(2, 0).getContent() == GameRole.CROSS);

		Assert.assertTrue(testingBoard.hasWon(GameRole.CROSS));
		out.println("scenario 2 - player X win in vertical line");
		out.println(testingBoard);
	}

	@Test
	public void scenarioThreePlayerOWinWithHorizontalLine() {

		Assert.assertNotNull(testingBoard);
		TicTacToePlayer player = new MinMaxPlayer(testingBoard, GameRole.KNOT);
		testingBoard.setCellAt(0, 0, GameRole.CROSS);
		testingBoard.setCellAt(0, 2, GameRole.CROSS);
		testingBoard.setCellAt(1, 0, GameRole.KNOT);
		testingBoard.setCellAt(1, 1, GameRole.KNOT);
		testingBoard.setCellAt(2, 0, GameRole.CROSS);
		player.move();

		Assert.assertTrue(testingBoard.getRoleAt(1, 2).getContent() == GameRole.KNOT);

		Assert.assertTrue(testingBoard.hasWon(GameRole.KNOT));
		out.println("scenario 3 - player O win in horizontal line");
		out.println(testingBoard);
	}

	@Test
	public void scenarioFourPlayerXWinWithDiagonalLine() {

		Assert.assertNotNull(testingBoard);
		TicTacToePlayer player = new MinMaxPlayer(testingBoard, GameRole.CROSS);
		testingBoard.setCellAt(0, 0, GameRole.CROSS);
		testingBoard.setCellAt(1, 0, GameRole.KNOT);
		testingBoard.setCellAt(1, 1, GameRole.CROSS);
		testingBoard.setCellAt(2, 0, GameRole.KNOT);
		player.move();

		Assert.assertTrue(testingBoard.getRoleAt(2, 2).getContent() == GameRole.CROSS);

		Assert.assertTrue(testingBoard.hasWon(GameRole.CROSS));
		out.println("scenario 4 - player X win in diagonal line");
		out.println(testingBoard);
	}

	@Test
	public void scenarioFiveDraw() {

		Assert.assertNotNull(testingBoard);
		TicTacToePlayer firstPlayer = new MinMaxPlayer(testingBoard, GameRole.CROSS),
				secondPlayer = new MinMaxPlayer(testingBoard, GameRole.KNOT);

		while (!testingBoard.isGameOver()) {

			firstPlayer.move();
			if (!testingBoard.isGameOver()) {

				secondPlayer.move();
			}
		}

		Assert.assertTrue(!testingBoard.hasWon(GameRole.CROSS) && !testingBoard.hasWon(GameRole.KNOT)
				&& testingBoard.getAvailableStates().isEmpty());

		out.println("scenario 5 - Draw");
		out.println(testingBoard);
	}
}
