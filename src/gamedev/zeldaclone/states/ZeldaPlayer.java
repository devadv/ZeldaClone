package gamedev.zeldaclone.states;

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

	private float x;
	protected float y;
	private float width = 25;
	private float HEIGHT = 25;
	protected float speed = 0.20f;
	private boolean isEnding = true;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		x = gc.getWidth() / 2 -140;
		y = gc.getHeight() -60;
	
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.green);
		g.fillRect(x, y, width, HEIGHT);
		g.setColor(Color.white);
		g.fillRect(67, 580, width, HEIGHT);
	
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
		if(gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			moveLeft(delta);
		}

		if(gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			moveRight(delta);
		}
		if(gc.getInput().isKeyDown(Input.KEY_UP)) {
			moveUp(delta);
		}
		if(gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			moveDown(delta);
		}
		if (x + speed < 50) {
			moveRight(delta);
		}
		if (x + speed > gc.getWidth() - width - 254) {
			moveLeft(delta);
		}
		
		//The code below is as a test, as leaving the current map, it will go to another.
		//In this case it will move to another testmap in State 0.
		if (y + speed > gc.getHeight() - HEIGHT + 10) {
			sbg.enterState(0);
			}
		if (y + speed < gc.getHeight() - HEIGHT - 625) {
			sbg.enterState(0);
			}
		}
	
		public void moveDown(int delta) {
			y += speed * delta;
		
	}
		public void moveUp(int delta) {
			y -= speed * delta;
		
	}
		public void moveRight(int delta) {
			x += speed * delta;
			
			
		}
		public void moveLeft(int delta) {
			x -= speed * delta;
		}
		
		
		public boolean getBounds(Shape shape) {
			return new Rectangle(x, y, width, HEIGHT).intersects(shape);
		}

		public float getX() {
			return x;
		}
		
		public float getY() {
			return y;
		}
		public float getWidth() {
			return this.width;
		}
	
	}

