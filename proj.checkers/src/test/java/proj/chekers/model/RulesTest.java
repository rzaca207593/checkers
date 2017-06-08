package proj.chekers.model;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proj.checkers.model.Board;
import proj.checkers.model.RulesUtils;

public class RulesTest {
	RulesUtils sut;

	@Before
	public void initialize() {
		sut = new RulesUtils();
	}

	@Test
	public void beatNullDraughTest() {

		assertFalse(sut.beatNullDraugh(Board.getInstance().getXY(6, 2), 1, 1));
	}

	@Test
	public void checkBeatRangeTest() {
		assertFalse(sut.beatAvailable(true));
		assertFalse(sut.beatAvailable(false));
		Board.getInstance().resetPosition();

		Board.getInstance().change(Board.getInstance().getXY(5, 1), 4, 0);
		Board.getInstance().change(Board.getInstance().getXY(2, 2), 3, 1);
		assertTrue(sut.beatAvailable(true));
		assertFalse(sut.beatAvailable(false));
		Board.getInstance().resetPosition();
		Board.getInstance().change(Board.getInstance().getXY(5, 1), 3, 1);

		assertTrue(sut.beatAvailable(false));

	}
}
