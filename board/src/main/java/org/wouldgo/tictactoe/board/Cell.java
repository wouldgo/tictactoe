package org.wouldgo.tictactoe.board;

import org.wouldgo.tictactoe.GameRole;

public final class Cell {
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

		if (this.content != GameRole.EMPTY) {

			throw new IllegalStateException("This cell is already assigned to " + this.content);
		}

		this.content = newContent;
	}

	void resetContent() {

		this.content = GameRole.EMPTY;
	}
}
