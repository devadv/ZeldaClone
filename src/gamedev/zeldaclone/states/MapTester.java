package gamedev.zeldaclone.states;

import java.util.ArrayList;

import gamdev.zeldaclone.entities.Player;
import gamedev.zeldaclone.level.Map;
import gamedev.zeldaclone.utils.Direction;

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
	private int TILESIZE = 32;
	
	public MapTester(int i) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new Map();
		player = new Player(2 *TILESIZE, 3 * TILESIZE);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		generateData();
		map.renderTileMap();
		// map.renderTileMapLayer(1);
		player.render(gc, sbg, g);
		// loop through data
		int y = 0;
		int x = 492;
		g.setColor(Color.red);
		for (String s : data) {
			g.drawString(s, x, y += 20);
		}
		//map.drawDebugLines(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		listenKeyboard(gc);

	}
	public boolean isBlocked(Direction dir){
		int posX = (int) player.getX()/TILESIZE;
		int posY = (int) player.getY()/TILESIZE;
		int layerindex = 0;
		if(!map.isBlocked(posX + dir.dX() , posY + dir.dY(), layerindex)){
			return false;
		}else{
			return true;
		}
	}
	public void listenKeyboard(GameContainer gc){
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_RIGHT)){
			
			if(!isBlocked(Direction.EAST)){
				player.setX(player.getX()+TILESIZE);
			}
			
		}
		if(input.isKeyPressed(Input.KEY_LEFT)){

			if(!isBlocked(Direction.WEST)){
				player.setX(player.getX()-TILESIZE);
			}
			
		}
		if(input.isKeyPressed(Input.KEY_UP)){

			if(!isBlocked(Direction.NORTH)){
				player.setY(player.getY()-TILESIZE);
			}
			
		}
		if(input.isKeyPressed(Input.KEY_DOWN)){

			if(!isBlocked(Direction.SOUTH)){
				player.setY(player.getY()+TILESIZE);
			}
			
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
		for (String obj : map.listObjects()) {
			data.add("Object = " + obj);
		}
	}



	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
