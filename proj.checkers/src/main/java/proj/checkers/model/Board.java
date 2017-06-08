package proj.checkers.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import proj.checkers.model.Team.GameColour;
import proj.checkers.model.draughtsman.CrownedDraughtsman;
import proj.checkers.model.movement.Position;
import proj.checkers.model.movement.PositionFactory;

//singleton 
public class Board {
	private static final Logger logger = LogManager.getLogger("Board");

	public static Board board;
	int dimension;
	PositionFactory PositionFactory;

	Position[][] gameboard = new Position[8][8];

	public Position[][] getListPosition() {
		return gameboard;
	}

	public boolean checkIfDraughtNull(int x, int y) {
		return Board.getInstance().getXY(x, y).draughtIsNull();
	}

	public Position getXY(int x, int y) {
		return gameboard[x][y];
	}

	private Board() {
		PositionFactory = new PositionFactory();
		PositionFactory.fill(gameboard);

	}

	public void resetPosition() {
		PositionFactory.fill(gameboard);
	}

	public static Board getInstance() {

		if (board == null) {
			board = new Board();
		}

		return board;

	}

	public void change(Position old, int _x, int _y) {
		logger.info("Zmiana pozycji wiersz "
				+ gameboard[old.getX()][old.getY()].getX());
		logger.info("Zmiana pozycji kolumna "
				+ gameboard[old.getX()][old.getY()].getY());
		gameboard[_x][_y].setDraught(gameboard[old.getX()][old.getY()]
				.getDraught());
		gameboard[old.getX()][old.getY()].setDraught(null);

		logger.info("Nowa pozycja pionka (" + _x + " , " + _y + " ) ");

		changeToCrowned(_x, _y);

	}

	public void changeToCrowned(int x, int y) {
		if ((x == 0) || (x == 7)) {
			if ((getXY(x, y).draughtIsNull() ? false : getXY(x, y).getDraught()
					.getColour() == GameColour.WHITE)
					|| (getXY(x, y).draughtIsNull() ? false : getXY(x, y)
							.getDraught().getColour() == GameColour.BLACK)) {
				logger.info("Zamian pionka (" + x + " , " + y + " ) w dame");
				getXY(x, y).setDraught(
						new CrownedDraughtsman(getXY(x, y).getDraught()));
			}
		}

	}

	public void setDraughtNull(int toRemoveX, int toRemoveY) {
		Board.getInstance().getXY(toRemoveX, toRemoveY).setDraught(null);

	}

}
