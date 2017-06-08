package proj.checkers.model.movement;


import proj.checkers.model.Team.GameColour;
import proj.checkers.model.draughtsman.Draughtsman;

public class PositionFactory {

	public PositionFactory() { 

	}

	public void fill(Position[][] gameboard) {
		double sqrt = Math.sqrt(64);

		for (int i = 0; i < sqrt; i++) {
			for (int j = 0; j < sqrt; j++) {

				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					// dostepne
					gameboard[i][j] = new Position(i, j, true);
				}

				else {
					// niedostepne
					gameboard[i][j] = new Position(i, j, false);
				}
			}
		}
		initialFillWithDraught(gameboard);
	}

	void initialFillWithDraught(Position[][] gameboard) {
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 8; i++)
				if (gameboard[j][i].isPermitted())
					gameboard[j][i]
							.setDraught(new Draughtsman(GameColour.BLACK));

		// gracz 1 - dol planszy
		for (int j = 5; j < 8; j++)
			for (int i = 0; i < 8; i++)
				if (gameboard[j][i].isPermitted())
					gameboard[j][i]
							.setDraught(new Draughtsman(GameColour.WHITE));
	}


}
