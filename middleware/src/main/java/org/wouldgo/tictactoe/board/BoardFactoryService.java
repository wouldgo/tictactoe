package org.wouldgo.tictactoe.board;

import org.springframework.stereotype.Service;

@Service
public interface BoardFactoryService {

	public Board makeBoard();
}
