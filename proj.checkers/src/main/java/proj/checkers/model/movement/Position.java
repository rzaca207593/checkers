package proj.checkers.model.movement;

import proj.checkers.model.draughtsman.DraughtChoice;

public class Position {

	private int x;
	private int y;
	boolean permission = false;
	DraughtChoice draught;

/*	public Position(Position another) {
		this.x = another.x;
		this.y = another.y;
		this.permission = another.permission;
		this.draught = another.draught;
	}
*/
	public DraughtChoice getDraught() {
		return draught;
	}

	public void setDraught(DraughtChoice draught) {
		this.draught = draught;
	}

	public Position(int i, int j, boolean b) {
		this.x = i;
		this.y = j;
		permission = b;

	}

	public Position(int i, int j) {
		this.x = i;
		this.y = j;
		permission = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isPermitted() {
		return permission;
	}

	public void setPermitted(boolean b) {
		permission = b;
	}

	public boolean draughtIsNull() {
		if (draught == null) {
			return true;
		}
		return false;
	}
}
