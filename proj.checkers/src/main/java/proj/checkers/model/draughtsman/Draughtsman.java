package proj.checkers.model.draughtsman;

import proj.checkers.model.Team.GameColour;
import proj.checkers.model.movement.RangeOfMovement;
import proj.checkers.model.movement.StandardRange;

public class Draughtsman implements DraughtChoice {

	final GameColour colour;
	RangeOfMovement rangeOfMovement;

	public Draughtsman(GameColour black) {
		this.colour = black;
		setRangeOfMovement();
	}

	public void setRangeOfMovement() {
		this.rangeOfMovement = new StandardRange();
	}

	public GameColour getColour() {
		return colour;
	}

}
