package proj.checkers.controller;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proj.checkers.controller.GameController;
import proj.checkers.model.Board;

public class GameControllerTest {

	GameController sut;
	@Before
	public void initializeSUT(){
		sut=new GameController();
	}
	
/*	@Test
	
	public void verifyTest(){

		assertTrue(sut.verify(255,166));
		assertFalse(sut.verify(1000, 2000));
	}
*/	
	@Test
	public void verifyAndCheckMoveTest(){
		sut.verifyAndSet(380, 175);
		sut.verifyAndCheckMove(145,337);
		assertFalse(Board.getInstance().checkIfDraughtNull(4, 0));		
	}
	
	@Test 
	public void newGameTest(){
		sut.verifyAndSet(380, 175);
		sut.verifyAndCheckMove(145,337);
		sut.newGame();
		assertTrue(Board.getInstance().checkIfDraughtNull(3, 1));
	}
	
	@Test
	public void endOfGameTest(){
		
		assertFalse(sut.checkIfEndOfGame());
	}
	
	@Test
	public void ifCrownedDraughtsmanTest(){
		Board.getInstance().changeToCrowned(0,2);
		Board.getInstance().changeToCrowned(0,1);

		assertTrue(sut.ifCrownedDraughtsman(0,2));
		assertFalse(sut.ifCrownedDraughtsman(0,1));	
	}
	
	@Test
	public void checkIfNoDraughtTest(){
		assertTrue(!sut.checkIfNoDraught(3,5));
	}
	
	@Test 
	public void checkIfDroughtWhiteTest(){
		assertTrue(sut.checkIfDroughtWhite(7,3));
		assertFalse(sut.checkIfDroughtWhite(0,6));
	}
	
	@Test
	public void checkIfPermittedTest(){
		assertFalse(sut.checkIfPermitted(0,5));
		assertTrue(sut.checkIfPermitted(0,6));

	}
}
