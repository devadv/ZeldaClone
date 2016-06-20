package gamedev.zeldaclone.test.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class ZeldaStates extends StateBasedGame {

	public ZeldaStates(String name) {
		super(name);

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new ZeldaMenu(0));
		addState(new ZeldaGame(1));
		addState(new ZeldaTester(2));
		enterState(2);

	}

	public static void main(String[] args) {
		try {
			AppGameContainer agc = new AppGameContainer(new ZeldaStates(
					"Zelda Clone Game"));
			agc.setDisplayMode(960, 640, false);
			agc.setTargetFrameRate(60);
			agc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
