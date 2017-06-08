package proj.chekers.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proj.checkers.model.Game;
import proj.checkers.model.Team.GameColour;

public class GameTest {

	
	Game sut;
	
	@Before
	public void initializeSUT(){
		sut=new Game();
	}
	
	@Test 
	public void activeTeamTest(){
		boolean active=true;
		assertTrue(sut.activeTeam()==active);
	}
	
	@Test 
	public void createTeamsTest(){
		assertTrue(sut.getTeamBlack().getColour()==GameColour.BLACK);
		assertNotNull(sut.getTeamBlack());
		assertNotNull(sut.getTeamWhite());
	}
	
	@Test
	public void pointForTest(){
		boolean white=true;
		sut.pointFor(white);
		assertTrue(sut.getTeamWhite().getScore()==1);
		sut.pointFor(!white);
		assertTrue(sut.getTeamBlack().getScore()==1);
	}
	
	@Test
	public void endOfGameTest(){
		assertFalse(sut.endOfGame());
		for(int i=0;i<12;i++){
		sut.pointFor(true);
		}
		assertTrue(sut.endOfGame());

	}
	
	
}
