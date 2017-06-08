package proj.checkers.model.draughtsman;

import proj.checkers.model.Team.GameColour;

//wrap gdy zamiana w dame
public class CrownedDraughtsman implements DraughtChoice{
	
	DraughtChoice draugtshman; 
	
	public CrownedDraughtsman(DraughtChoice draugtsman){
		this.draugtshman=draugtsman;
	}
	
	@Override
	public GameColour getColour() {
		return draugtshman.getColour();
	}
}


