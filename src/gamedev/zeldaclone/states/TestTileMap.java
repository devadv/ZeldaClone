package gamedev.zeldaclone.states;


import java.awt.Font;

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

	private TiledMap tilemap;
	private Font awtFont;
	private TrueTypeFont ttf;
	private int TS = 32;
	private boolean debug;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		tilemap = new TiledMap("res/testtilemap2.tmx");
		awtFont = new Font("RetGanon", Font.PLAIN, 20);
		ttf = new TrueTypeFont(awtFont, false);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.drawString("getLayerIndex Wall / Grass : " + tilemap.getLayerIndex("Wall") +" " + tilemap.getLayerIndex("Grass"), 492, 50);
		g.drawString("getWidth: Width in tiles: " + tilemap.getWidth(),492, 70);
		tilemap.render(0, 0);
		g.setBackground(Color.orange);
		if(debug) {
			drawDebugLines(g, TS);;
		}

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_D)) {
			debug = !debug;
		}
	}
	public void drawDebugLines(Graphics g, int size) {
		int resolutions = 480;
		for(int i = 0; i < resolutions; i += size) {
			g.setColor(Color.white);
			g.drawLine(i,  0,  i,  resolutions);
			g.drawLine(0,  i,  resolutions, i);
				
			
		}
	}
	
}
