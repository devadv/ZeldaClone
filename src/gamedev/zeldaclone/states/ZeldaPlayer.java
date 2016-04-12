package gamedev.zeldaclone.states;

import gamedev.zeldaclone.utils.Position;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class ZeldaPlayer {

	private int x;
	protected int y;
	private int width = 32;
	private int height = 32;
	protected float speed = 0.20f;
	private boolean isEnding = true;
	private int screenWidth;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		x =2 * width;
		y = 3 * height;
		screenWidth = gc.getWidth();

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		//g.fillRect(67, 580, width, height);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		/*
		 * if(gc.getInput().isKeyDown(Input.KEY_LEFT)) { moveLeft(delta); }
		 * 
		 * if(gc.getInput().isKeyDown(Input.KEY_RIGHT)) { moveRight(delta); }
		 * if(gc.getInput().isKeyDown(Input.KEY_UP)) { moveUp(delta); }
		 * if(gc.getInput().isKeyDown(Input.KEY_DOWN)) { moveDown(delta); } if
		 * (x + speed < 50) { moveRight(delta); } if (x + speed > gc.getWidth()
		 * - width - 254) { moveLeft(delta); }
		 */

		// The code below is as a test, as leaving the current map, it will go
		// to another.
		// In this case it will move to another testmap in State 0.
		// if (y + speed > gc.getHeight() - height + 10) {
		// sbg.enterState(0);
		// }
		// if (y + speed < gc.getHeight() - height - 625) {
		// sbg.enterState(0);
		// }
	}

	public void moveDown() {
		y += 32;

	}

	public void moveUp() {
		y -= 32;

	}

	public void moveRight() {
		x += 32;

	}

	public void moveLeft() {
		x -= 32;
	}

	public boolean getBounds(Shape shape) {
		return new Rectangle(x, y, width, height).intersects(shape);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float getWidth() {
		return this.width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public Position getPosition() {
		return new Position(x, y);
		
	}

}
