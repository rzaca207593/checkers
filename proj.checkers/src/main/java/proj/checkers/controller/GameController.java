package proj.checkers.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import proj.checkers.model.Game;
import proj.checkers.model.Team.GameColour;
import proj.checkers.model.draughtsman.CrownedDraughtsman;

public class GameController {
	private static final Logger logger = LogManager.getLogger("GameControl");
	Game game;

	public GameController() { 
		game = new Game();
	}

	public void verifyAndSet(int y, int x) {
		if (verify(y, x)) {
			int _x, _y;
			_x = (x - 20 - 100) / 40;
			_y = (y - 40 - 120) / 40;

			logger.info("verifyAndSet (" + _y + " , " + _x + ")");
			game.setOld(_y, _x);
		}

	}

	private boolean verify(int y, int x) {
		x = x - 100;
		y = y - 120;
		return (x >= 20 && x < 340 && y >= 40 && y < 360 && (x + 22) % 40 != 0
				&& (x + 21) % 40 != 0 && (y + 2) % 40 != 0 && (y + 1) % 40 != 0);

	}

	public void verifyAndCheckMove(int x, int y) {

		if (verify(y, x)) {
			logger.info("verifyAndCheckMove (" + countX(y) + " , " + countY(x)
					+ ")");
			game.checkMove(countX(y), countY(x));
		}
	}

	int countY(int y) {
		return (y - 20 - 100) / 40;
	}

	int countX(int x) {
		return (x - 40 - 120) / 40;
	}

	public void newGame() {
		game = new Game();
		game.resetGame();
	}

	public boolean checkIfEndOfGame() {
		return game.endOfGame();
	}

	public boolean ifCrownedDraughtsman(int i, int j) {
		if (game.getBoard().getXY(i, j).getDraught() instanceof CrownedDraughtsman) {
			return true;
		}
		return false;
	}

	public boolean checkIfNoDraught(int i, int j) {
		return (!game.getBoard().checkIfDraughtNull(i, j));
	}

	public boolean checkIfDroughtWhite(int i, int j) {
		return game.getBoard().getXY(i, j).getDraught().getColour() == GameColour.WHITE;
	}

	public boolean checkIfPermitted(int i, int j) {
		return game.getBoard().getXY(i, j).isPermitted();
	}

}
