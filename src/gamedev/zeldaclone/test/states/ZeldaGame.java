package gamedev.zeldaclone.test.states;

import gamedev.zeldaclone.utils.Position;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class ZeldaGame extends BasicGameState {

	private ZeldaPlayer player;
	private TiledMap tileMap;
	private int TILESIZE = 32;
	private int layerIndex;
	private String layer;
	private int offsetX = 5 * TILESIZE;
	private int offsetY = 3 * TILESIZE;

	public ZeldaGame(int i) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		tileMap = new TiledMap("res/house_A.tmx");
		player = new ZeldaPlayer();
		player.init(gc, sbg);
		Position position = getStartPos();
		player.setX(position.getX() + offsetX);
		player.setY(position.getY() + offsetY);
		layer = "foreground";
		layerIndex = tileMap.getLayerIndex(layer);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		tileMap.render(offsetX, offsetY);
		player.render(gc, sbg, g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if (gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			if (!isBlocked(-1, 0)) {

				player.moveLeft();

			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {

			if (!isBlocked(1, 0)) {

				player.moveRight();

			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_UP)) {

			if (!isBlocked(0, -1)) {

				player.moveUp();

			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN)) {

			if (!isBlocked(0, 1)) {

				player.moveDown();

			}
		}

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

			int valueID = tileMap.getTileId((player.getX() - offsetX / TILESIZE) + coordinateX,
					player.getY() - offsetY / TILESIZE + coordinateY, layerIndex);

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
			tileID = tileMap.getTileId((player.getX() - offsetX) / TILESIZE + coordinateX,
					(player.getY() - offsetY) / TILESIZE + coordinateY, layerIndex);
		} catch (ArrayIndexOutOfBoundsException e) {

			return 0;
		}
		return tileID;
	}

	public boolean isBlocked(int coordinateX, int coordinateY) {
		int tileID = getNextTileID(coordinateX, coordinateY);
		// System.out.println("X : " + coordinateX + " Y : " + coordinateY);
		String value = tileMap.getTileProperty(tileID, "blocked", "false");
		if (value.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getID() {
		// TODO
		return 1;
	}

}
