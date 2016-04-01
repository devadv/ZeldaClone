package gamedev.zeldaclone.states;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class TestTileMap1 {

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

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		tileMap = new TiledMap("res/testtilemap2.tmx");
		awtFont = new Font("RetGanon", Font.PLAIN, 20);
		ttf = new TrueTypeFont(awtFont, false);
		layer = "Wall";
		layerIndex = tileMap.getLayerIndex(layer);
		data = new ArrayList<String>();
		player = new ZeldaPlayer();
		player.init(gc, sbg);
		player.setX(3 * TILESIZE);
		player.setY(3 * TILESIZE);
		mapWidth = tileMap.getWidth();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// g.drawString("getWidth: Width in tiles: " + tileMap.getWidth(),492,
		// 70);
		tileMap.render(0, 0);
		//tileMap.render(0, 0, 0, 0, 50, 50, 2, true);
		g.setBackground(Color.orange);
		player.render(gc, sbg, g);
		if (debug) {
			drawDebugLines(g, TILESIZE);
			drawTiledIDs(g);
		}
		// loop through data
		int y = 0;
		int x = 492;
		g.setColor(Color.red);
		for (String s : data) {
			g.drawString(s, x, y += 20);
		}

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		generateData();
		layerIndex = tileMap.getLayerIndex(layer);
		player.update(gc, sbg, delta);
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_D)) {
			debug = !debug;
		}
		if (debug) {
			if (input.isKeyPressed(Input.KEY_1)) {
				layer = "Wall";
			}
			if (input.isKeyPressed(Input.KEY_2)) {
				layer = "Grass";
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			if (neighbourExist(-1, 0)) {
				if (!(getNextTileID(-1, 0) == 25)) {
					player.moveLeft();
				}
			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {

			if (neighbourExist(1, 0)) {
				if (!(getNextTileID(1, 0) == 25)) {
					player.moveRight();
				}
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_UP)) {
			if (neighbourExist(0, -1)) {
				if(!(getNextTileID(0, -1) == 25)) {
					player.moveUp();
				}
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			if (neighbourExist(0, 1)) {
				if (!(getNextTileID(0, 1) == 25)) {
					player.moveDown();
				}
			}
		}
	}

	public void generateData() {
		data = new ArrayList<String>();
		data.add("Choose D for debug mode");
		data.add("-------------------------");
		data.add("getLayerIndex Wall / Grass: " + tileMap.getLayerIndex("Wall")
				+ " " + tileMap.getLayerIndex("Grass"));
		data.add("getLayerIndex: " + layerIndex);
		data.add("getWidth / getHeight: " + tileMap.getWidth() + " / "
				+ tileMap.getHeight());
		data.add("getTileWidth: " + tileMap.getTileWidth());
		data.add("getTileHeight: " + tileMap.getTileHeight());
		data.add("getTileId: Field 1 from Wall = "
				+ tileMap.getTileId(0, 0, tileMap.getLayerIndex("Wall")));
		data.add("getTileId: Field 1 from Grass = "
				+ tileMap.getTileId(0, 0, tileMap.getLayerIndex("Grass")));
		data.add("PlayerX, Y: " + player.getX() + " " + player.getY());
		data.add("PlayerTiledId "
				+ tileMap.getTileId(player.getX() / TILESIZE, player.getY()
						/ TILESIZE, layerIndex));
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

	}

	public void drawDebugLines(Graphics g, int size) {
		int resolutions = 480;
		for (int i = 0; i < resolutions; i += size) {
			g.setColor(Color.white);
			g.drawLine(i, 0, i, resolutions);
			g.drawLine(0, i, resolutions, i);

		}
	}

	public void drawTiledIDs(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < tileMap.getWidth(); i++) {
			for (int j = 0; j < tileMap.getHeight(); j++) {
				g.drawString("" + tileMap.getTileId(i, j, layerIndex),
						(i * TILESIZE) + 5, (j * TILESIZE) + 5);
			}
		}
	}
	/**
	 * Checks if it's the end of the map.
	 * @param coordinateX The X coordinates of the direction the player moves.
	 * @param coordinateY The Y coordinates of the direction the player moves.
	 * @return true if tile exists. And false if it doesn't exist.
	 * <pre>
	 * Coordinates example.
	 * 		North (0, -1), 
	 * 		East (1, 0),
	 * 		South (0, 1), 
	 * 		West (-1, 0).
	 * </pre>
	 */
	public boolean neighbourExist(int coordinateX, int coordinateY) {

		try {

			int valueID = tileMap.getTileId((player.getX() / TILESIZE)
					+ coordinateX, player.getY() / TILESIZE + coordinateY,
					layerIndex);

		} catch (ArrayIndexOutOfBoundsException e) {

			return false;
		}
		return true;

	}
	/**
	 * 
	 * @param coordinateX The X coordinates of the direction the player moves.
	 * @param coordinateY The Y coordinates of the direction the player moves.
	 * @return true if tile exists. And false if it doesn't exist.
	 */

	public int getNextTileID(int coordinateX, int coordinateY) {
		int tileID;
		try {
			tileID = tileMap.getTileId(
					(player.getX() / TILESIZE) + coordinateX, player.getY()
							/ TILESIZE + coordinateY, layerIndex);
		} catch (ArrayIndexOutOfBoundsException e) {

			return 0;
		}
		return tileID;
	}

}
