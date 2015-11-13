package org.wouldgo.tictactoe.game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.wouldgo.tictactoe.dto.GameStatus;

public final class GamesState {
	private final Map<Integer, TicTacToeGame> states = new HashMap<>();
	private final AtomicInteger incr = new AtomicInteger(0);
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock read, write;

	public GamesState() {

		read = lock.readLock();
		write = lock.writeLock();
	}

	public int putGame(TicTacToeGame state) {

		try {
			this.write.lock();
			int id = incr.incrementAndGet();
			this.states.put(id, state);
			return id;
		} finally {

			this.write.unlock();
		}
	}

	public GameStatus makeMoveOnGame(int id) {

		try {
			this.read.lock();
			return this.states.get(id).makeMove();
		} finally {

			this.read.unlock();
		}
	}

	public void removeGame(int id) {

		try {
			this.write.lock();
			this.states.remove(id);
		} finally {

			this.write.unlock();
		}
	}
}
