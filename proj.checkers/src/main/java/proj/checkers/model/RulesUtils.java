package proj.checkers.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import proj.checkers.model.Team.*;
import proj.checkers.model.movement.Position;
import proj.checkers.model.movement.RangeOfMovement;

public class RulesUtils {

	private static final Logger logger = LogManager.getLogger("RulesUtils");
	List<Position> activeColl;

	public Map<GameColour, Team> createTeams(Map<GameColour, Team> team) {
		team = new HashMap<GameColour, Team>();
		team.put(GameColour.WHITE, new Team(GameColour.WHITE));
		logger.info("Team WHITE jest stworzona ");
		team.put(GameColour.BLACK, new Team(GameColour.BLACK));
		logger.info("Team BLACK jest stworzona ");
		return team;
	}


	public boolean beatAvailable(boolean properColor) {
		activeColl = new ArrayList<Position>();
		boolean beat = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Board.getInstance().getXY(i, j).isPermitted()
						&& properColor) {
					if (Board.getInstance().checkIfDraughtNull(i, j) ? false
							: Board.getInstance().getXY(i, j).getDraught()
									.getColour() == GameColour.WHITE) {
						activeColl.add(Board.getInstance().getXY(i, j));
					}

				} else if (Board.getInstance().getXY(i, j).isPermitted()
						&& (!properColor)) {
					if (Board.getInstance().checkIfDraughtNull(i, j) ? false
							: Board.getInstance().getXY(i, j).getDraught()
									.getColour() == GameColour.BLACK) {

						activeColl.add(Board.getInstance().getXY(i, j));

					}
				}
			}

		}
		if (properColor) {
			beat = checkWhiteRange();
		} else {
			beat = checkBlackRange();
		}
		return beat;
	}

	private boolean checkBlackRange() {

		for (Position element : activeColl) {
			if ((element.getY() == 7 || element.getY() == 6)&& element.getX() < 6) {

				if (checkElementBeat(element, 1, -1, GameColour.WHITE)
						&& beatNullDraugh(element, 2, -2)) {

					return true;
				}
			}

			if ((element.getY() == 0 || element.getY() == 1) && element.getX() < 6) {
				if (checkElementBeat(element, 1, 1, GameColour.WHITE)
						&& beatNullDraugh(element, 2, 2)) {
					return true;
				}
			}

			if (element.getY() > 1 && (element.getX() < 6)
					&& element.getY() < 6) {
				if ((checkElementBeat(element, 1, -1, GameColour.WHITE) && beatNullDraugh(
						element, 2, -2))
						|| (checkElementBeat(element, 1, 1, GameColour.WHITE) && beatNullDraugh(
								element, 2, 2))) {
					return true;
				}
			}
		}
		return false;
	}

	 private boolean checkWhiteRange() {
		for (Position element : activeColl) {

			if ((element.getY() == 7 || element.getY() == 6) && element.getX() > 1) {
				if (checkElementBeat(element, -1, -1, GameColour.BLACK)
						&& beatNullDraugh(element, -2, -2)) {
					return true;
				}
			}

			if ((element.getY() == 0 || element.getY() == 1) && element.getX() > 1) {
				if (checkElementBeat(element, -1, 1, GameColour.BLACK)
						&& beatNullDraugh(element, -2, 2))
					return true;
			}

			if (element.getX() > 1 && element.getY() > 1 && element.getY() < 6) {
				if ((checkElementBeat(element, -1, -1, GameColour.BLACK) && beatNullDraugh(
						element, -2, -2))
						|| (checkElementBeat(element, -1, 1, GameColour.BLACK) && beatNullDraugh(
								element, -2, 2))) {
					return true;
				}
			}

		}
		return false;
	}

	public boolean beatNullDraugh(Position element, int x, int y) {
		return Board.getInstance()
				.getXY(element.getX() + x, element.getY() + y).draughtIsNull() ? true
				: false;
	}

	public boolean checkElementBeat(Position element, int x, int y,
			GameColour gameColor) {

		return Board.getInstance()
				.getXY(element.getX() + x, element.getY() + y).draughtIsNull() ? false
				: (Board.getInstance()
						.getXY(element.getX() + x, element.getY() + y)
						.getDraught().getColour() == gameColor);
	}


	public void beatRangeValidation(RangeOfMovement standardRange, int _x, int _y) {
		int toRemoveX = standardRange.getOld().getX() - _x;
		int toRemoveY = standardRange.getOld().getY() - _y;

		if (toRemoveX < 0)
			toRemoveX = standardRange.getOld().getX() + 1;
		else
			toRemoveX = standardRange.getOld().getX() - 1;

		if (toRemoveY < 0)
			toRemoveY = standardRange.getOld().getY() + 1;
		else
			toRemoveY = standardRange.getOld().getY() - 1;

		Board.getInstance().setDraughtNull(toRemoveX, toRemoveY);		
	}

}
