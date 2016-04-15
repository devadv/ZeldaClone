package gamedev.zeldaclone.test.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class ZeldaGame extends BasicGameState {

	private TiledMap tilemap;
	private ZeldaPlayer player;

	public ZeldaGame(int i) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	tilemap = new TiledMap("res/testmap.tmx");
	player = new ZeldaPlayer();
	player.init(gc, sbg);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		tilemap.render(0, 0);
		player.render(gc, sbg, g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update(gc, sbg, delta); 
		

	}

	@Override
	public int getID() {
		// TODO 
		return 1;
	}

}
