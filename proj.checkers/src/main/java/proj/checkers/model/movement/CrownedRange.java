package proj.checkers.model.movement;

import proj.checkers.model.Board;

public class CrownedRange extends RangeOfMovement {

	private boolean available;
	private int signX;
	private int signY;

	public CrownedRange() {
		super();
	}

	public boolean checkRange(int x, int y) {
		returns = false;
		this.x = x;
		this.y = y;
		check();
		return returns;
	}

	private void check() {
		toCheck = Board.getInstance().getXY(x, y);
		if (checkMovePermission()) {
			for (int i = 0; i < 8; i++) {
				if ((toCheck.getX() == old.getX() - i && toCheck.getY() == old
						.getY() - i)
						|| (toCheck.getX() == old.getX() - i && toCheck.getY() == old
								.getY() + i)
						|| (toCheck.getX() == old.getX() + i && toCheck.getY() == old
								.getY() - i)
						|| (toCheck.getX() == old.getX() + i && toCheck.getY() == old
								.getY() + i)) {
					if (toCheck.getX() - old.getX() > 0) {
						signX = 1;
					} else {
						signX = -1;
					}
					if (toCheck.getY() - old.getY() > 0) {
						signY = 1;
					} else {
						signY = -1;
					}
					returns = true;
					available = true;
					for (int j = 1; j < Math.abs(toCheck.getX() - old.getX()); j++) {
						for (int k = 1; k < Math.abs(toCheck.getY()
								- old.getY()); k++) {
							if (!Board.getInstance().checkIfDraughtNull(
									old.getX() + j * signX,
									old.getY() + k * signY)) {
								available = false;
							}
						}
					}

					returns = returns && available;
				}
			}

		}
	}

}
