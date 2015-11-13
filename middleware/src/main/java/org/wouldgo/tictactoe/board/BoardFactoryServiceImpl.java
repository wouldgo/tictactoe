package org.wouldgo.tictactoe.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
final class BoardFactoryServiceImpl implements BoardFactoryService {

	private static final transient Logger logger = LoggerFactory.getLogger(BoardFactoryServiceImpl.class);

	@Override
	public Board makeBoard() {

		if (logger.isInfoEnabled()) {

			logger.info("Generating a new board...");
		}
		return new Board();
	}
}
