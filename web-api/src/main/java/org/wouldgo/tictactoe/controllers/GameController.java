package org.wouldgo.tictactoe.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wouldgo.tictactoe.dto.GameStatus;
import org.wouldgo.tictactoe.game.GameFactory;
import org.wouldgo.tictactoe.game.GamesState;
import org.wouldgo.tictactoe.game.TicTacToeGame;

@Controller
public class GameController {

	private static final transient Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameFactory gameFactory;

	@Autowired
	private GamesState gameState;

	@RequestMapping(value = "/new-game", method = RequestMethod.POST)
	public @ResponseBody int createNewGame() {

		if (logger.isInfoEnabled()) {

			logger.info("Called POST - /new-game");
		}

		TicTacToeGame gameCreated = this.gameFactory.createGame();
		return gameState.putGame(gameCreated);
	}

	@RequestMapping(value = "/make-move-in-game", method = RequestMethod.GET)
	public @ResponseBody GameStatus makeMove(@RequestParam int id) {

		if (logger.isInfoEnabled()) {

			logger.info("Called GET - /make-move-in-game");
		}
		return this.gameState.makeMoveOnGame(id);
	}

	@RequestMapping(value = "/remove-game", method = RequestMethod.DELETE)
	public @ResponseBody void removeGame(@RequestParam int id) {

		if (logger.isInfoEnabled()) {

			logger.info("Called DELETE - /remove-game");
		}
		this.gameState.removeGame(id);
	}
}
