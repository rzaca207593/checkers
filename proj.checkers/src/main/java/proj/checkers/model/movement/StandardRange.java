package proj.checkers.model.movement;

import proj.checkers.model.Board;
import proj.checkers.model.Team.GameColour;

public class StandardRange extends RangeOfMovement {

	public StandardRange() {
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

		return returns;
	}

	private void checkWhite() {
		toCheck = Board.getInstance().getXY(x, y);
		if (checkMovePermission()) {
			if ((toCheck.getX() == old.getX() - 1 && toCheck.getY() == old
					.getY() - 1)
					|| (toCheck.getX() == old.getX() - 1 && toCheck.getY() == old
							.getY() + 1)) {
				returns = true;
			}

		}
	}

	private void checkBlack() {
		toCheck = Board.getInstance().getXY(x, y);
		if (checkMovePermission()) {
			if ((toCheck.getX() == old.getX() + 1 && toCheck.getY() == old
					.getY() - 1)
					|| (toCheck.getX() == old.getX() + 1 && toCheck.getY() == old
							.getY() + 1)) {
				returns = true;
			}

		}
	}

}
