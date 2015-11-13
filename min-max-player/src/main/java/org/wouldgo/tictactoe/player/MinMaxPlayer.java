package org.wouldgo.tictactoe.player;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wouldgo.tictactoe.GameRole;
import org.wouldgo.tictactoe.board.Board;
import org.wouldgo.tictactoe.board.Cell;

import com.google.common.collect.ImmutableList;

public final class MinMaxPlayer extends TicTacToePlayer {
	private static final transient Logger logger = LoggerFactory.getLogger(MinMaxPlayer.class);

	private static final int DEPTH = 2;

	public MinMaxPlayer(Board board, GameRole role) {

		super(board, role);
		if (logger.isDebugEnabled()) {

			logger.debug("Creating a MinMaxPlayer");
		}
	}

	@Override
	public void move() {

		if (logger.isDebugEnabled()) {

			logger.debug("Moving...");
		}
		int[] result = minimax(DEPTH, myRole);
		if (result[1] != -1 && result[2] != -1) {

			this.board.setCellAt(result[1], result[2], myRole);
		}
	}

	private int[] minimax(int depth, GameRole player) {

		Collection<Cell> nextMoves = generateMoves();
		int bestScore = (player == myRole) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;

		if (nextMoves.isEmpty() || depth == 0) {

			bestScore = evaluate();
		} else {
			for (Cell aFreeCell : nextMoves) {

				int xPos = aFreeCell.getX(), yPos = aFreeCell.getY();
				this.board.setCellAt(xPos, yPos, player);
				if (player == myRole) {

					currentScore = minimax(depth - 1, hisRole)[0];
					if (currentScore > bestScore) {

						bestScore = currentScore;
						bestRow = xPos;
						bestCol = yPos;
					}
				} else {

					currentScore = minimax(depth - 1, myRole)[0];
					if (currentScore < bestScore) {
						bestScore = currentScore;
						bestRow = xPos;
						bestCol = yPos;
					}
				}

				this.board.resetCellAt(xPos, yPos);
			}
		}
		return new int[] { bestScore, bestRow, bestCol };
	}

	private Collection<Cell> generateMoves() {

		if (this.board.hasWon(myRole) || this.board.hasWon(hisRole)) {

			return ImmutableList.<Cell> of();
		}

		return ImmutableList.<Cell> copyOf(this.board.getAvailableStates());
	}

	private int evaluate() {

		int score = 0;
		score += evaluateLine(0, 0, 0, 1, 0, 2);
		score += evaluateLine(1, 0, 1, 1, 1, 2);
		score += evaluateLine(2, 0, 2, 1, 2, 2);
		score += evaluateLine(0, 0, 1, 0, 2, 0);
		score += evaluateLine(0, 1, 1, 1, 2, 1);
		score += evaluateLine(0, 2, 1, 2, 2, 2);
		score += evaluateLine(0, 0, 1, 1, 2, 2);
		score += evaluateLine(0, 2, 1, 1, 2, 0);
		return score;
	}

	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
		int score = 0;

		if (this.board.getRoleAt(row1, col1).getContent() == myRole) {

			score = 1;
		} else if (this.board.getRoleAt(row1, col1).getContent() == hisRole) {

			score = -1;
		}

		if (this.board.getRoleAt(row2, col2).getContent() == myRole) {

			if (score == 1) {

				score = 10;
			} else if (score == -1) {

				return 0;
			} else {

				score = 1;
			}
		} else if (this.board.getRoleAt(row2, col2).getContent() == hisRole) {

			if (score == -1) {

				score = -10;
			} else if (score == 1) {

				return 0;
			} else {

				score = -1;
			}
		}

		if (this.board.getRoleAt(row3, col3).getContent() == myRole) {

			if (score > 0) {

				score *= 10;
			} else if (score < 0) {

				return 0;
			} else {

				score = 1;
			}
		} else if (this.board.getRoleAt(row3, col3).getContent() == hisRole) {

			if (score < 0) {

				score *= 10;
			} else if (score > 1) {

				return 0;
			} else {

				score = -1;
			}
		}
		return score;
	}
}
