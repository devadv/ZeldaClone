package gamedev.zeldaclone.states;

import java.util.ArrayList;

import gamdev.zeldaclone.entities.Player;
import gamedev.zeldaclone.level.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapTester extends BasicGameState {

	private Map map;
	private ArrayList<String> data;
	private Player player;
	public MapTester(int i) {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		map = new Map();
		player = new Player(2 * 32, 3 * 32);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		generateData();
		map.renderTileMap();
		//map.renderTileMapLayer(1);
		player.render(gc, sbg, g);
		// loop through data
				int y = 0;
				int x = 492;
				g.setColor(Color.red);
				for (String s : data) {
					g.drawString(s, x, y += 20);
					
				}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		Input input = gc.getInput();
		if (gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			player.moveLeft();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {
			player.moveRight();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_UP)) {
			player.moveUp();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			player.moveDown();
		}
		
	}
	public void generateData() {
		data = new ArrayList<String>();
		data.add("Welcome to the test.");
		data.add("mapWidth = " + map.getWidth());
		data.add("mapHeight = " + map.getHeight());
		data.add("numberOfLayers = " + map.numberOfLayers());
		data.add("numberOfObjectLayers = " + map.numberOfObjectLayers());
		data.add("Position: 0,0 = " + map.isBlocked(0, 0, 0));
		data.add("Position: 10,6 = " + map.isBlocked(10, 6, 0));
		for(String obj:map.listObjects()) {
		data.add("Object = " + obj);
		}
	}
	public boolean isBlocked() {
		int posX = player.getX() / 32;
		int posY = player.getY() / 32;
		return true;
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
