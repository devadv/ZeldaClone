package gamdev.zeldaclone.entities;

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

public class Player {

	private int x;
	protected int y;
	private int width = 32;
	private int height = 32;
	protected float speed = 0.20f;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
