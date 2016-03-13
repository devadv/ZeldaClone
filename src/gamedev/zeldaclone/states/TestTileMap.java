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

public class TestTileMap {

	private TiledMap tileMap;
	private Font awtFont;
	private TrueTypeFont ttf;
	private boolean debug;
	private int layerIndex;
	private int TILESIZE = 32;
	private ArrayList<String> data;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		tileMap = new TiledMap("res/testtilemap2.tmx");
		awtFont = new Font("RetGanon", Font.PLAIN, 20);
		ttf = new TrueTypeFont(awtFont, false);
		String layer = "Wall";
		layerIndex = tileMap.getLayerIndex(layer);
		data = new ArrayList<String>();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//g.drawString("getWidth: Width in tiles: " + tileMap.getWidth(),492, 70);
		tileMap.render(0, 0);
		g.setBackground(Color.orange);
		if(debug) {
			drawDebugLines(g, TILESIZE);
			drawTiledIDs(g);
		}
		//loop through data
		int y = 0;
		int x = 492;
		g.setColor(Color.red);
		for (String s : data){
			g.drawString(s, x, y+=20);
		}

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		generateData();
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_D)) {
			debug = !debug;
		}
	}
	public void generateData(){
		data = new ArrayList<String>();
		data.add("Choose D for debug mode");
		data.add("-------------------------");
		data.add("getLayerIndex Wall / Grass : " + tileMap.getLayerIndex("Wall") +" " + tileMap.getLayerIndex("Grass"));
		
	}
	
	public void drawDebugLines(Graphics g, int size) {
		int resolutions = 480;
		for(int i = 0; i < resolutions; i += size) {
			g.setColor(Color.white);
			g.drawLine(i,  0,  i,  resolutions);
			g.drawLine(0,  i,  resolutions, i);
				
			
		}
	}
	public void drawTiledIDs(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < tileMap.getWidth(); i++) {
			for (int j = 0; j < tileMap.getHeight(); j++) {
				g.drawString("" + tileMap.getTileId(i, j, layerIndex), (i * TILESIZE ) + 5,
						(j * TILESIZE) + 5);
			}
		}
	}
	
}
