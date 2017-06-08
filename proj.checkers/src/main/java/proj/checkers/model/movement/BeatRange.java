package proj.checkers.model.movement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import proj.checkers.model.Board;
import proj.checkers.model.Team.GameColour;

public class BeatRange extends RangeOfMovement {
	private static final Logger logger = LogManager.getLogger("BeatRange");

	public BeatRange() {
		super();
	}

	public boolean checkRange(int x, int y) {
		returns = false;
		this.x = x;
		this.y = y;
		if (color != null ? color == GameColour.WHITE : false) {
			checkWhite();
		} else if (color != null ? color == GameColour.BLACK : false) {
			checkBlack();
		}
		logger.info("Range jest w statusie " + returns);
		return returns;
	}

	private void checkBlack() {
		toCheck = Board.getInstance().getXY(x, y);

		if ((old.getY() == 7 || old.getY() == 6) && old.getX() < 6) {

			if (checkElementBeat(toCheck, 1, -1, GameColour.WHITE)
					&& beatNullDraugh(toCheck, 2, -2)) {

				returns = true;
			}
		}

		if ((old.getY() == 0 || old.getY() == 1) && old.getX() < 6) {
			if (checkElementBeat(toCheck, 1, 1, GameColour.WHITE)
					&& beatNullDraugh(toCheck, 2, 2)) {
				returns = true;
			}
		}

		if (old.getY() > 1 && (old.getX() < 6) && old.getY() < 6) {
			if ((checkElementBeat(toCheck, 1, -1, GameColour.WHITE) && beatNullDraugh(
					toCheck, 2, -2))
					|| (checkElementBeat(toCheck, 1, 1, GameColour.WHITE) && beatNullDraugh(
							toCheck, 2, 2))) {
				returns = true;
			}
		}
	}

	private void checkWhite() {
		toCheck = Board.getInstance().getXY(x, y);

		if ((old.getY() == 7 || old.getY() == 6) && old.getX() > 1) {
			if (checkElementBeat(toCheck, -1, -1, GameColour.BLACK)
					&& beatNullDraugh(toCheck, -2, -2)) {
				returns = true;
			}
		}

		if ((old.getY() == 0 || old.getY() == 1) && old.getX() > 1) {
			if (checkElementBeat(toCheck, -1, 1, GameColour.BLACK)
					&& beatNullDraugh(toCheck, -2, 2))
				returns = true;
		}

		if (old.getX() > 1 && old.getY() > 1 && old.getY() < 6) {
			if ((checkElementBeat(toCheck, -1, -1, GameColour.BLACK) && beatNullDraugh(
					toCheck, -2, -2))
					|| (checkElementBeat(toCheck, -1, 1, GameColour.BLACK) && beatNullDraugh(
							toCheck, -2, 2))) {
				returns = true;
			}
		}

	}

	public boolean beatNullDraugh(Position element, int x, int y) {
		return (element.getX() == old.getX() + x)
				&& (element.getY() == old.getY() + y)
				&& Board.getInstance().checkIfDraughtNull(old.getX() + x, old.getY() + y)
						? true : false;
	}

	public boolean checkElementBeat(Position element, int x, int y,
			GameColour gameColor) {

		return (Board.getInstance().checkIfDraughtNull(old.getX() + x, old.getY() + y)
				 ? false
				: (Board.getInstance().getXY(old.getX() + x, old.getY() + y)
						.getDraught().getColour() == gameColor));
	}

}
