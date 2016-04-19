package gamedev.zeldaclone.level;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author nrotmeyer
 *
 */

public class Map {
	/** Position X of the map, relative to the top left corner. */
	private int x = 0;
	/** Position Y of the map, relative to the top left corner. */
	private int y = 0;
	/** Uses the format .tmx to load the map. **/
	private TiledMap tileMap;

	/** Default constructor with standard map. */
	public Map() {
		try {
			tileMap = new TiledMap("res/testtilemap15.tmx");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Shows the map at the default position, which is 0,0. */
	public void renderTileMap() {
		tileMap.render(x, y);
	}
	/**This is for the second layer.
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
	/**Gets the tile id. 
	 * @param x, y*/
	public int getTileID(int x, int y, int layerIndex) {
		return tileMap.getTileId(x, y, layerIndex);
		
	}
	/**Checks tile property blocked is true. 
	 * @param x, y coordinates
	 * @param layerIndex*/
	public boolean isBlocked(int x, int y, int layerIndex) {
		int tileID = getTileID(x, y, layerIndex);
		String value = tileMap.getTileProperty(tileID, "blocked", "false");
		if (value.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
}
