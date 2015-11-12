package org.wouldgo.tictactoe.board;

import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wouldgo.tictactoe.GameRole;

public class TestBoard {

	private static Board testingBoard;

	@BeforeClass
	public static final void onlyOnce() {

		testingBoard = new Board();
	}

	@Test
	public void shouldBeNotNull() {

		Assert.assertNotNull(testingBoard);
	}

	@Test
	public void shouldGetCells() {

		Collection<Cell> availableStates = testingBoard.getAvailableStates();
		Assert.assertNotNull(availableStates);
		Assert.assertTrue(!availableStates.isEmpty());
	}

	@Test
	public void shouldReturnNotEmpty() {

		for (Cell aCell : testingBoard.getAvailableStates()) {

			Assert.assertNotNull(aCell);
			Assert.assertTrue(testingBoard.getRoleAt(aCell.getX(), aCell.getY()).getContent() != null);
		}
	}

	@Test
	public void shouldResetTheCellState() {

		testingBoard.setCellAt(2, 2, GameRole.CROSS);
		testingBoard.resetCellAt(2, 2);
		testingBoard.setCellAt(2, 2, GameRole.KNOT);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowAnIllegalStateException() {

		testingBoard.setCellAt(0, 0, GameRole.CROSS);

		Assert.assertTrue(testingBoard.getRoleAt(0, 0).getContent() == GameRole.CROSS);

		testingBoard.setCellAt(0, 0, GameRole.KNOT);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowAnIllegalStateException2() {

		testingBoard.setCellAt(0, 0, GameRole.CROSS);

		Assert.assertTrue(testingBoard.getRoleAt(0, 0).getContent() == GameRole.CROSS);

		testingBoard.setCellAt(0, 0, GameRole.CROSS);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnIllegalArgumentException() {

		testingBoard.setCellAt(12, 0, GameRole.CROSS);
	}
}
