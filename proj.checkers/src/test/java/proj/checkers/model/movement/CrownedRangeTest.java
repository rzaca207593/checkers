package proj.checkers.model.movement;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import proj.checkers.model.Board;
import proj.checkers.model.WrongPositionException;

public class CrownedRangeTest {

RangeOfMovement sut;
	
	@Before 
	public void initialize(){
		sut=new CrownedRange();
		
	}
	
	@Test
	public void checkRangeCrowned(){
		Board.getInstance().resetPosition();
		initialize();
		Board.getInstance().change(Board.getInstance().getXY(5, 3), 4, 2);
		Board.getInstance().change(Board.getInstance().getXY(2, 4), 3, 3);
		try {
			sut.setOldPosition(Board.getInstance().getXY(4, 2), true);
		} catch (WrongPositionException e) {
			e.printStackTrace();
		}
		assertFalse(sut.checkRange(2, 4));
		Board.getInstance().resetPosition();
		assertFalse(sut.checkRange(2, 4));
	}
	
	@Test
	public void checkRangeBeatCrowned(){
		Board.getInstance().resetPosition();
		Board.getInstance().change(Board.getInstance().getXY(5, 3), 4, 2);
		Board.getInstance().change(Board.getInstance().getXY(2, 4), 3, 3);
		sut= new CrownedBeatRange();
		try {
			sut.setOldPosition(Board.getInstance().getXY(4, 2), true);
		} catch (WrongPositionException e) {
			e.printStackTrace();
		}
		assertTrue(sut.checkRange(2, 4));
		Board.getInstance().resetPosition();
		assertFalse(sut.checkRange(2, 4));
	}
}
