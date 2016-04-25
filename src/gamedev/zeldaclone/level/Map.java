package gamedev.zeldaclone.level;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author nrotmeyer & badev
 *
 */

public class Map {
	/** Position x of the map, relative to the top left corner. */
	private int x = 0;
	/** Position y of the map, relative to the top left corner. */
	private int y = 0;
	/** Uses the format .tmx to load the map. **/
	private TiledMap tileMap;
	/** the tilesize in pixels */
	private int TILESIZE = 32;

	/** Default constructor with standard map. */
	public Map() {
		try {
			tileMap = new TiledMap("res/testtilemap15.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor for tiledmap file in tmx format
	 * 
	 * @param ref
	 *            the String reference to *.tmx file
	 * 
	 * */

	public Map(String ref) {

		try {
			tileMap = new TiledMap(ref);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	/** Shows the map at the default position, which is 0,0. */
	public void renderTileMap() {
		tileMap.render(x, y);
	}

	/**
	 * This is for the second layer.
	 * 
	 * @param layerIndex
	 */
	public void renderTileMapLayer(int layerIndex) {
		tileMap.render(x, y, layerIndex);
	}

	/** The map width in tiles. */
	public int getWidth() {
		return tileMap.getWidth();
	}

	/** The map height in tiles. */
	public int getHeight() {
		return tileMap.getHeight();
	}

	/** Retrieve the number of layers. */
	public int numberOfLayers() {
		return tileMap.getLayerCount();

	}

	/** Retrive number of object layers. */
	public int numberOfObjectLayers() {
		return tileMap.getObjectGroupCount();

	}

	/**
	 * Retrieve the number of objects.
	 * 
	 * @param groupID
	 *            objectLayer
	 * 
	 * */

	public int numberOfObjects(int groupID) {
		return tileMap.getObjectCount(groupID);

	}

	/** List of objects. */
	public ArrayList<String> listObjects() {
		ArrayList<String> objects = new ArrayList<String>();
		for (int i = 0; i < numberOfObjectLayers(); i++) {
			for (int j = 0; j < numberOfObjects(i); j++) {
				objects.add(tileMap.getObjectName(i, j));
			}
		}
		return objects;

	}

	/**
	 * Retrieve the tile id.
	 * 
	 * @param x
	 *            postion of x
	 * @param y
	 *            position of y
	 * @param layerIndex
	 *            the index number of the layer
	 */
	public int getTileID(int x, int y, int layerIndex) {
		return tileMap.getTileId(x, y, layerIndex);

	}

	/**
	 * Checks if tile property blocked is true.
	 * 
	 * @param x
	 *            position of x
	 * @param y
	 *            position of y
	 * @param layerIndex
	 *            the index number of the layer
	 */
	public boolean isBlocked(int x, int y, int layerIndex) {
		int tileID = getTileID(x, y, layerIndex);
		String value = tileMap.getTileProperty(tileID, "blocked", "false");
		if (value.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * for debug purposes
	 * 
	 * @param g
	 *            Graphics form Slick2d
	 **/
	public void drawDebugLines(Graphics g) {
		int resolutions = getWidth() * TILESIZE;
		for (int i = 0; i < resolutions; i += TILESIZE) {
			g.setColor(Color.white);
			g.drawLine(i, 0, i, resolutions);
			g.drawLine(0, i, resolutions, i);

		}
	}
	public void setTileID(int x, int y, int layerIndex, int tileid) {
		tileMap.setTileId(x, y, layerIndex, tileid);
	}

}
