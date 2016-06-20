package gamedev.zeldaclone.test.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class ZeldaTester extends BasicGameState {

	private TestTileMap testtilemap;
	private ZeldaPlayer player;

	public ZeldaTester(int i) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		testtilemap = new TestTileMap();
		testtilemap.init(gc, sbg);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		testtilemap.render(gc, sbg, g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		testtilemap.update(gc, sbg, delta);
		
		

	}

	@Override
	public int getID() {
		// TODO 
		return 2;
	}

}
