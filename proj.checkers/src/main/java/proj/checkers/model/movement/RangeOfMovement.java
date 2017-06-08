package proj.checkers.model.movement;

import proj.checkers.model.WrongPositionException;
import proj.checkers.model.Team.GameColour;

public abstract class RangeOfMovement {

	Position toCheck;
	Position old;
	GameColour color;
	boolean returns = false;
	protected int y;
	protected int x;
	private boolean activeWhite;

	public void setOldPosition(Position old) {
		this.old = old;
		this.color = old.getDraught().getColour();
	}

	public abstract boolean checkRange(int x, int y);

	public Position getOld() {
		return old;
	}

	boolean checkMovePermission() {

		return (toCheck.draughtIsNull() && toCheck.isPermitted());
	}

	public void setOldPosition(Position xy, boolean activeWhite)
			throws WrongPositionException {
		this.activeWhite = activeWhite;
		if (colorIsProper(xy))
			setOldPosition(xy);
		else
			throw new WrongPositionException("Niepoprawne zachowanie");
	}

	public boolean colorIsProper(Position xy) {
		if (xy==null || xy.getDraught()==null){
			return false;
		}
		return ((activeWhite && xy.getDraught().getColour() == GameColour.WHITE) || (!activeWhite && xy
				.getDraught().getColour() == GameColour.BLACK));
	}

}
