package proj.checkers.model;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import proj.checkers.model.Team.GameColour;
import proj.checkers.model.Team.Team;
import proj.checkers.model.draughtsman.CrownedDraughtsman;
import proj.checkers.model.movement.BeatRange;
import proj.checkers.model.movement.CrownedBeatRange;
import proj.checkers.model.movement.CrownedRange;
import proj.checkers.model.movement.RangeOfMovement;
import proj.checkers.model.movement.StandardRange;

public class Game {
	private static final Logger logger = LogManager.getLogger("Game"); 
	
	RangeOfMovement standardRange;
	Map<GameColour, Team> team;
	boolean activeWhite = true;
	RulesUtils ruleUtils = new RulesUtils();
	public int END_OF_GAME = 12;
	
	public void resetGame() {
		Board.getInstance().resetPosition();
	}

	public boolean activeTeam() {
		return activeWhite;
	}

	public Game() {
		startGame();
		//this.team = ruleUtils.createTeams(team);
	}
	
	public Game(int endOfGame) {
		
		if (endOfGame > 0) {
			this.END_OF_GAME = endOfGame;
		}
		startGame();
		//this.team = ruleUtils.createTeams(team);
	}

	public Team getTeamWhite() {
		return team.get(GameColour.WHITE);
	}

	public Team getTeamBlack() {
		return team.get(GameColour.BLACK);
	}

	public void createTeams() {
		this.team=ruleUtils.createTeams(team);
	}

	public Board getBoard() {
		return Board.getInstance();
	}

	public void startGame() {

		createTeams();
		this.standardRange = new StandardRange();
	}



	public void checkMove(int _x, int _y) {
		if (standardRange.checkRange(_x, _y)) {
			logger.info("checkMove " + standardRange.getOld().getX());
			Board.getInstance().change(standardRange.getOld(), _x, _y);
			
			if (standardRange instanceof BeatRange || standardRange instanceof CrownedBeatRange) {
				ruleUtils.beatRangeValidation(standardRange, _x, _y);
				pointFor(activeWhite);
			}
			if(ruleUtils.beatAvailable(activeWhite)&& standardRange instanceof BeatRange || standardRange instanceof CrownedBeatRange ){
				logger.info("Kolejne bicie mo¿liwe");
				setOld(_x, _y);
			}
			else{
			activeWhite = !activeWhite;
			}
		}
	}

	public void setOld(int _x, int _y) {
		logger.info(ruleUtils.beatAvailable(activeWhite));
		
		
		if (ruleUtils.beatAvailable(activeWhite)) {
			if(standardRange instanceof CrownedRange){
				standardRange = new CrownedBeatRange();
			}
			else {
				standardRange = new BeatRange();
			}
			logger.info("Ustawiamy ruch bicia");
		} else if (!ruleUtils.beatAvailable(activeWhite)) {
			if (Board.getInstance().checkIfDraughtNull(_x, _y) ? false
					: (Board.getInstance().getXY(_x, _y).getDraught() instanceof CrownedDraughtsman)) {
				logger.info("Ruch damki");

				standardRange = new CrownedRange();
			} else {
				logger.info("Ruch standardowego pionka");
				standardRange = new StandardRange();

			}
		}
		try {
			standardRange.setOldPosition(Board.getInstance().getXY(_x, _y),
					activeWhite);
		} catch (WrongPositionException e) {
			logger.error("Zla pozycja " + e.getMessage());
		}
	}

	public void pointFor(boolean activeWhite) {
		logger.info("Punkt dla druzyny ");
		if (activeWhite) {
			getTeamWhite().increaseScore();
		} else {
			getTeamBlack().increaseScore();
		}

	}

	public boolean endOfGame() {
		if ((getTeamBlack().getScore() == END_OF_GAME) || (getTeamWhite().getScore() == END_OF_GAME)) {
			return true;
		}
		return false;
	}
}
