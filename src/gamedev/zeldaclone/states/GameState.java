package gamedev.zeldaclone.states;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends StateBasedGame {
	
	private Image image;

	public GameState(String title) {
		super(title);
		
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MapTester(0));
		enterState(0);
		
	}
	
	

	public static void main(String[] args) {
		
		try {
			AppGameContainer agc = new AppGameContainer(new
			GameState("MapTester"));
			agc.setDisplayMode(1024,640,false);
			agc.setShowFPS(false);
			agc.start();
			} catch(SlickException e) {
			e.printStackTrace();
			}		
	}

}
