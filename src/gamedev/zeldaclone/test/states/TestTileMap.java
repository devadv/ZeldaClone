package gamedev.zeldaclone.test.states;

import gamedev.zeldaclone.utils.Position;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class TestTileMap {

	private TiledMap tileMap;
	private Font awtFont;
	private TrueTypeFont ttf;
	private boolean debug;
	private int layerIndex;
	private int TILESIZE = 32;
	private ArrayList<String> data;
	private String layer;
	private ZeldaPlayer player;
	private int mapWidth;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		tileMap = new TiledMap("res/house_A.tmx");
		awtFont = new Font("RetGanon", Font.PLAIN, 20);
		ttf = new TrueTypeFont(awtFont, false);
		layer = "foreground";
		layerIndex = tileMap.getLayerIndex(layer);
		data = new ArrayList<String>();
		player = new ZeldaPlayer();
		player.init(gc, sbg);
		Position position = getStartPos();
		player.setX(position.getX());
		player.setY(position.getY());
		// Position position = getStartPos();
		// player.setX(position.getX());
		// player.setY(position.getY());

		mapWidth = tileMap.getWidth();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// g.drawString("getWidth: Width in tiles: " + tileMap.getWidth(),492,
		// 70);
		tileMap.render(0, 0);
		player.render(gc, sbg, g);

		// tileMap.render(0, 0, tileMap.getLayerIndex ("Wall"));
		// tileMap.render(0, 0, 0, 0, 32, 32, 0, true);
		g.setBackground(Color.orange);
		// player.render(gc, sbg, g);
		if (debug) {
			drawDebugLines(g, TILESIZE);
			drawTiledIDs(g);
		}
		// loop through data
		int y = 0;
		int x = 640;
		g.setColor(Color.red);
		for (String s : data) {
			g.drawString(s, x, y += 20);
		}

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		generateData();
		layerIndex = tileMap.getLayerIndex(layer);
		player.update(gc, sbg, delta);
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_D)) {
			debug = !debug;
		}
		if (debug) {
			if (input.isKeyPressed(Input.KEY_1)) {
				layer = "Walls";
			}
			if (input.isKeyPressed(Input.KEY_2)) {
				layer = "Water";
			}
			if (input.isKeyPressed(Input.KEY_3)) {
				layer = "Treasure";
			}
			if (input.isKeyPressed(Input.KEY_4)) {
				layer = "Stairs";
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			if (neighbourExist(-1, 0)) {
				if (!(getNextTileID(-1, 0) == 26)) {
									
					player.moveLeft();
					
				}
			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {

			if (neighbourExist(1, 0)) {
				if (!(getNextTileID(1, 0) == 26)) {
					player.moveRight();
				}
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_UP)) {
			if (neighbourExist(0, -1)) {
				if (!(getNextTileID(0, -1) == 26)) {
					player.moveUp();
				}
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			if (neighbourExist(0, 1)) {
				if (!(getNextTileID(0, 1) == 26)) {
					// layer = "Treasure";
					if (!(getNextTileID(0, 1) == 465)) {

						player.moveDown();
					}
				}
			}
		}
		if (player.getPosition().getX() == getEndPos().getX()
				&& player.getPosition().getY() == getEndPos().getY()) {
			tileMap = new TiledMap("res/testtilemap13.tmx");
			Position position = getStartPos();
			player.setX(position.getX());
			player.setY(position.getY());
			//System.out.println("End.");
		}
		if (player.getPosition().getX() == getStartPos().getX()
				&& player.getPosition().getY() == getStartPos().getY()) {
			//System.out.println("Start.");
		}
	}

	public void generateData() {
		data = new ArrayList<String>();
		data.add("Choose D for debug mode");
		data.add("-------------------------");
		// data.add("getLayerIndex Wall / Grass: " +
		// tileMap.getLayerIndex("Walls")
		// + " " + tileMap.getLayerIndex("Water"));
		data.add("getLayerIndex: " + layerIndex);
		data.add("getWidth / getHeight: " + tileMap.getWidth() + " / " + tileMap.getHeight());
		data.add("getTileWidth: " + tileMap.getTileWidth());
		data.add("getTileHeight: " + tileMap.getTileHeight());
		// data.add("getTileId: Field 1 from Wall = "
		// + tileMap.getTileId(0, 0, tileMap.getLayerIndex("Wall")));

		data.add("PlayerX, Y: " + player.getX() + " " + player.getY());
		// data.add("PlayerTiledId "
		// + tileMap.getTileId(player.getX() / TILESIZE, player.getY()
		// / TILESIZE, layerIndex));
		if (neighbourExist(0, -1)) {
			data.add("North: " + getNextTileID(0, -1));
		} else {
			data.add("North:  Doesn't exist");
		}
		if (neighbourExist(1, 0)) {
			data.add("East: " + getNextTileID(1, 0));

		} else {
			data.add("East:  Doesn't exist");
		}
		if (neighbourExist(0, 1)) {
			data.add("South: " + getNextTileID(0, 1));

		} else {
			data.add("South:  Doesn't exist");
		}
		if (neighbourExist(-1, 0)) {
			data.add("West: " + getNextTileID(-1, 0));

		} else {
			data.add("West:  Doesn't exist");
		}
		data.add("");
		data.add("");
		data.add("");
		int objectCount = tileMap.getObjectCount(0);
		data.add("objectCount: " + objectCount);
		for (int i = 0; i < objectCount; i++) {
			data.add("* object: " + tileMap.getObjectName(0, i));
			data.add("Rect :" + tileMap.getObjectName(0, i)
			+ " Center : " 
			+ getRectangleObject(i).getCenterX()+ "," 
			+ getRectangleObject(i).getCenterY() 
			);
		}
		
		data.add("objectID for table " + getObjectID("table"));
		if(player.getBounds(getRectangleObject(getObjectID("table")))){
			data.add("collision with table");
		}
		data.add("objectID for bed" + getObjectID("bed"));
		if(player.getBounds(getRectangleObject(getObjectID("bed")))){
			data.add("collision with bed");
		}
		

	}

	public void drawDebugLines(Graphics g, int size) {
		int resolutions = 960;
		for (int i = 0; i < resolutions; i += size) {
			g.setColor(Color.white);
			g.drawLine(i, 0, i, resolutions);
			g.drawLine(0, i, resolutions, i);

		}
	}

	public void drawTiledIDs(Graphics g) {
		g.setColor(Color.white);
		for (int i = 0; i < tileMap.getWidth(); i++) {
			for (int j = 0; j < tileMap.getHeight(); j++) {
				g.drawString("" + tileMap.getTileId(i, j, layerIndex), (i * TILESIZE) + 5,
						(j * TILESIZE) + 5);
			}
		}
	}

	/**
	 * Checks if it's the end of the map.
	 * 
	 * @param coordinateX
	 *            The X coordinates of the direction the player moves.
	 * @param coordinateY
	 *            The Y coordinates of the direction the player moves.
	 * @return true if tile exists. And false if it doesn't exist.
	 * 
	 *         <pre>
	 * Coordinates example.
	 * 		North (0, -1), 
	 * 		East (1, 0),
	 * 		South (0, 1), 
	 * 		West (-1, 0).
	 * </pre>
	 */
	public boolean neighbourExist(int coordinateX, int coordinateY) {

		try {

			int valueID = tileMap.getTileId((player.getX() / TILESIZE) + coordinateX, player.getY()
					/ TILESIZE + coordinateY, layerIndex);

		} catch (ArrayIndexOutOfBoundsException e) {

			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param coordinateX
	 *            The X coordinates of the direction the player moves.
	 * @param coordinateY
	 *            The Y coordinates of the direction the player moves.
	 * @return true if tile exists. And false if it doesn't exist.
	 */

	public int getNextTileID(int coordinateX, int coordinateY) {
		int tileID;
		try {
			tileID = tileMap.getTileId((player.getX() / TILESIZE) + coordinateX, player.getY()
					/ TILESIZE + coordinateY, layerIndex);
		} catch (ArrayIndexOutOfBoundsException e) {

			return 0;
		}
		return tileID;
	}

	public Position getStartPos() {
		int startPosX = 0;
		int startPosY = 0;
		int groupID = 0;
		String objectName1 = tileMap.getObjectName(groupID, 0);
		if (objectName1.equals("start")) {
			startPosX = tileMap.getObjectX(groupID, 0);
			startPosY = tileMap.getObjectY(groupID, 0);
		} else {
			System.out.println("start object has to be the first one");
		}

		return new Position(startPosX, startPosY);
	}

	public Position getEndPos() {

		int endPosX = 0;
		int endPosY = 0;
		int groupID = 0;
		String objectName1 = tileMap.getObjectName(groupID, 1);
		if (objectName1.equals("end")) {
			endPosX = tileMap.getObjectX(groupID, 1);
			endPosY = tileMap.getObjectY(groupID, 1);
		} else {
			System.out.println("end object has to be the second one");
		}
		return new Position(endPosX, endPosY);

	}

	public Rectangle getRectangleObject(int objID) {
		int groupID = 0; 
		int x = tileMap.getObjectX(groupID, objID);
		int y = tileMap.getObjectY(groupID, objID); 
		int width = tileMap.getObjectWidth(groupID, objID);
		int height = tileMap.getObjectHeight(groupID, objID);
		
		return new Rectangle(x, y, width, height);

	}

	public int getObjectID(String name) {

		int objectCount = tileMap.getObjectCount(0);
		int groupID = 0;
		int objID = 0;
		for (int i = 0; i < objectCount; i++) {
			String objName = tileMap.getObjectName(groupID, i);
			if (objName.equals(name)) {
				objID = i;
				return objID;
			} else {
				objID = -1;
				return objID;
			}
		}
		return objID;
		
		
	}
	
}
