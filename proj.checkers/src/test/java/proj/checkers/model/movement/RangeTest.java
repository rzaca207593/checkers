package proj.checkers.model.movement;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proj.checkers.model.Board;
import proj.checkers.model.WrongPositionException;

public class RangeTest {

	RangeOfMovement sut;
	
	@Before 
	public void initialize(){
		sut=new BeatRange();
	}
	
	@Test
	public void checkRangeWhite(){
		Board.getInstance().change(Board.getInstance().getXY(5, 3), 4, 2);
		Board.getInstance().change(Board.getInstance().getXY(2, 4), 3, 3);

		try {
			sut.setOldPosition(Board.getInstance().getXY(4, 2), true);
		} catch (WrongPositionException e) {
			e.printStackTrace();
		}
		assertTrue(sut.checkRange(2, 4));
		Board.getInstance().resetPosition();
		assertFalse(sut.checkRange(2, 4));

	}
	@Test
	public void checkRangeBlack(){
		Board.getInstance().resetPosition();
		Board.getInstance().change(Board.getInstance().getXY(5, 1), 4, 0);
		Board.getInstance().change(Board.getInstance().getXY(1, 3), 2, 2);
		Board.getInstance().change(Board.getInstance().getXY(4, 0), 3, 1);

		try {
			sut.setOldPosition(Board.getInstance().getXY(2, 2), false);
		} catch (WrongPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(sut.checkRange(4, 0));
		Board.getInstance().resetPosition();
		assertFalse(sut.checkRange(4, 0));

	}
}
