package gamedev.zeldaclone.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class ZeldaStates extends StateBasedGame {

	public ZeldaStates(String name) {
		super(name);

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		try {
			AppGameContainer agc = new AppGameContainer(new ZeldaStates(
					"Zelda Clone Game"));
			agc.setDisplayMode(800, 600, false);
			agc.setTargetFrameRate(60);
			agc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
