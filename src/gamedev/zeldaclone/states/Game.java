package gamedev.zeldaclone.states;

import gamdev.zeldaclone.entities.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGameState {

	private TiledMap tilemap;
	private Player player;

	public Game(int i) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	tilemap = new TiledMap("res/testmap.tmx");
	
	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		tilemap.render(0, 0);
		

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		

	}

	@Override
	public int getID() {
		// TODO 
		return 1;
	}

}
