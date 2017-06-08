package proj.chekers.model;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proj.checkers.model.Game;
import proj.checkers.model.movement.Position;


public class BoardTest {

	Game sut;
	
	@Before
	public void initializeSUT() {
		sut = new Game();
	}
	
	@Test
	public void shouldOneFieldPositive() throws Exception {
		//given
		Position old = sut.getBoard().getXY(5, 1);
		//when
		sut.getBoard().change(old, 4, 0);;
		//then
		assertNull(sut.getBoard().getXY(5, 1).getDraught());
		assertNotNull(sut.getBoard().getXY(4, 0).getDraught());
	}


	
}
