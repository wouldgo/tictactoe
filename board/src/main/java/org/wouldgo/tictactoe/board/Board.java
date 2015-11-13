package org.wouldgo.tictactoe.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wouldgo.tictactoe.GameRole;

import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.ImmutableList;

public final class Board {

	private static final transient Logger logger = LoggerFactory.getLogger(Board.class);
	static final int ROWS = 3, COLS = 3;
	private final Cell[][] cells = { { new Cell(0, 0), new Cell(0, 1), new Cell(0, 2) },
			{ new Cell(1, 0), new Cell(1, 1), new Cell(1, 2) }, { new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) } };

	public Board() {
		if (logger.isDebugEnabled()) {

			logger.debug("Instantiating a board...");
		}
	}

	public Cell getRoleAt(int row, int col) {

		if (logger.isTraceEnabled()) {

			logger.trace("Getting role at {1}, {2}", row, col);
		}
		if (row <= ROWS && col <= COLS) {

			return this.cells[row][col];
		}

		throw new IllegalArgumentException("You provide row or column value greater than the board limits");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ROWS; i++) {

			for (int j = 0; j < COLS; j++) {

				sb.append(cells[i][j].getContent());
				if (j != COLS - 1) {

					sb.append(" | ");
				}
			}
			sb.append(StandardSystemProperty.LINE_SEPARATOR.value());
			sb.append("------ ------ ------");
			sb.append(StandardSystemProperty.LINE_SEPARATOR.value());
		}

		return sb.toString();
	}

	public void resetCellAt(int row, int col) {

		if (logger.isTraceEnabled()) {

			logger.trace("Resetting role at {1}, {2}", row, col);
		}
		this.cells[row][col].resetContent();
	}

	public void setCellAt(int row, int col, GameRole role) {

		if (logger.isTraceEnabled()) {

			logger.trace("Set role at {1}, {2} as {3}", row, col, role);
		}
		if (row <= ROWS && col <= COLS) {

			this.cells[row][col].setContent(role);
		} else {

			throw new IllegalArgumentException("You provide row or column value greater than the board limits");
		}
	}

	public Collection<Cell> getAvailableStates() {

		if (logger.isTraceEnabled()) {

			logger.trace("Getting avaiable states");
		}
		List<Cell> toReturn = new ArrayList<>();
		for (int i = 0; i < ROWS; ++i) {

			for (int j = 0; j < COLS; ++j) {

				if (cells[i][j] != null && cells[i][j].getContent() == GameRole.EMPTY) {

					toReturn.add(cells[i][j]);
				}
			}
		}
		return ImmutableList.<Cell> copyOf(toReturn);
	}

	public boolean hasWon(GameRole role) {
		if ((cells[0][0].getContent() == cells[1][1].getContent()
				&& cells[0][0].getContent() == cells[2][2].getContent() && cells[0][0].getContent() == GameRole.CROSS)
				|| (cells[0][2].getContent() == cells[1][1].getContent()
						&& cells[0][2].getContent() == cells[2][0].getContent() && cells[0][2].getContent() == role)) {

			return true;
		}
		for (int i = 0; i < ROWS; ++i) {

			if (cells[i][0].getContent() == cells[i][1].getContent()
					&& cells[i][0].getContent() == cells[i][2].getContent() && cells[i][0].getContent() == role) {

				return true;
			}
		}

		for (int j = 0; j < COLS; ++j) {
			if (cells[0][j].getContent() == cells[1][j].getContent()
					&& cells[0][j].getContent() == cells[2][j].getContent() && cells[0][j].getContent() == role) {

				return true;
			}
		}

		return false;
	}

	public boolean isGameOver() {

		return (hasWon(GameRole.CROSS) || hasWon(GameRole.KNOT) || getAvailableStates().isEmpty());
	}
}