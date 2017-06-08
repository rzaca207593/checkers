package proj.checkers.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class MainViewTest {

	MainView sut;
	@Before 
	public void initialize(){
		
	}
	
	@Test
	public void drawBoard(){
		Window window = new Window();
		window.setVisible(false);
		assertFalse(window.isVisible());
	}
}
