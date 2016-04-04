package gamedev.zeldaclone.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class ZeldaMenu extends BasicGameState {

	private TestTileMap2 testtilemap;

	public ZeldaMenu(int i) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		testtilemap = new TestTileMap2();
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
		return 0;
	}

}
