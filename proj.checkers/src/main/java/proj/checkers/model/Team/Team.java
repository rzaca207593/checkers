package proj.checkers.model.Team;

public class Team {
	public final GameColour colour;
	int score;

	public Team(GameColour gc) {
		this.colour = gc;
		this.score = 0;
	}

	public void increaseScore() {
		score = score + 1;
	}

	public int getScore() {
		return score;
	}

	public GameColour getColour() {
		return colour;
	}

}
