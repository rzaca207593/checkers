package proj.chekers.model;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proj.checkers.model.Board;
import proj.checkers.model.RulesUtils;
import proj.checkers.model.movement.StandardRange;

public class MoveCheck {

StandardRange sut;
RulesUtils rf;
	
	@Before
	public void initializeSUT() {
		sut = new StandardRange();
		rf=new RulesUtils();
	}

	@Test
	public void shouldBeBeatenWhite() throws Exception {
		//given
		Board.getInstance().resetPosition();
		//when
		Board.getInstance().change(Board.getInstance().getXY(5, 1), 4, 0);
		Board.getInstance().change(Board.getInstance().getXY(2, 2), 3, 1);
//		sut.setOldPosition(Board.getInstance().getXY(4, 0), true);
		//then 
		assertTrue(rf.beatAvailable(true));
	}
	
	@Test
	public void shouldBeBeatenBlack() throws Exception {
		//given
		Board.getInstance().resetPosition();
		
		//when
		Board.getInstance().change(Board.getInstance().getXY(7, 5), 6, 4);
		Board.getInstance().change(Board.getInstance().getXY(2, 2), 3, 1);
		Board.getInstance().change(Board.getInstance().getXY(5, 1), 4, 0);
//		sut.setOldPosition(Board.getInstance().getXY(4, 0), true);
		//then 
		assertTrue(rf.beatAvailable(true));

	}
	
	
	@Test
	public void checkMoves() throws Exception {
		//given
		Board.getInstance().resetPosition();
		
		//when

		//then
		/*assertNotNull(Board.getInstance().getXY(4, 0).getDraught());
		assertNotNull(Board.getInstance().getXY(3, 1).getDraught());
		assertNull(Board.getInstance().getXY(5, 1).getDraught());
		assertNull(Board.getInstance().getXY(2, 2).getDraught());*/
		assertFalse(sut.checkRange(4, 2));
		sut.setOldPosition(Board.getInstance().getXY(5, 1), true);
		assertTrue(sut.checkRange(4, 0));
		assertFalse(sut.checkRange(4, 5));
		
		sut.setOldPosition(Board.getInstance().getXY(2, 0), false);
		assertTrue(sut.checkRange(3, 1));
		assertFalse(sut.checkRange(5, 5));

		

	}
	
	@Test 
	public void checkIfDraughtAvailable(){
		assertTrue(Board.getInstance().checkIfDraughtNull(7, 2));
	}


}
