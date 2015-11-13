package org.wouldgo.tictactoe.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wouldgo.tictactoe.GameRole;

public final class Cell {

	private static final transient Logger logger = LoggerFactory.getLogger(Cell.class);
	private final int x, y;
	private GameRole content;

	Cell(int x, int y) {
		this.content = GameRole.EMPTY;
		this.x = x;
		this.y = y;
	}

	public GameRole getContent() {

		return this.content;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	void setContent(GameRole newContent) {

		if (logger.isDebugEnabled()) {

			logger.debug("Setting content {1} for {2}, {3}", newContent, x, y);
		}

		if (this.content != GameRole.EMPTY) {

			throw new IllegalStateException("This cell is already assigned to " + this.content);
		}

		this.content = newContent;
	}

	void resetContent() {

		this.content = GameRole.EMPTY;
	}
}
