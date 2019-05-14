package DDCombat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import creature.creature;

public class editorWindow extends JPanel {
	private mainWindow main;
	private static gridMap map = new gridMap();
	
	/** The list of tile images to render the map */
	private Image[] tiles = new Image[8];
	/** The offscreen buffer used for rendering in the wonder world of Java 2D */
	private Image buffer;
    private int ox = 0;
    private int oy = 0;
	
	public editorWindow() {
		super(new BorderLayout());
		try {
			tiles[gridMap.GRASS] = ImageIO.read(getResource("res/grass.png"));
			tiles[gridMap.TREES] = ImageIO.read(getResource("res/trees.png"));
			tiles[gridMap.WATER] = ImageIO.read(getResource("res/water.png"));
			//tiles[gridMap.PLAYER] = ImageIO.read(getResource("res/player.png"));
			//tiles[gridMap.GOBLIN] = ImageIO.read(getResource("res/goblin.png"));
		} catch (IOException e) {
			System.err.println("Failed to load resources: "+e.getMessage());
			System.exit(0);
		}
		
		addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent e) { 
				 ox = e.getX();
		         oy = e.getY();
		     }

			 public void mouseReleased (MouseEvent e) {
				 double dx = (e.getX() - ox)/64;
				 dx = dx+1;
				 double dy = (e.getY() - oy)/64;
				 dy= dy + 1;
				 System.out.println(ox/64 + " " + oy/64 + " " + dx + " " + dy);
				 if (e.getButton() == 1)
					 map.fillArea(ox/64, oy/64, (int)dx, (int)dy, map.TREES);
				 else 
					 map.fillArea(ox/64, oy/64, (int)dx, (int)dy, map.GRASS);
				 repaint(0);
				}
			 
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			/* 
				int dx = e.getX() - mousePt.x;
                int dy = e.getY() - mousePt.y;
                origin.setLocation(origin.x + dx, origin.y + dy);
                mousePt = e.getPoint();
                repaint();
                System.out.println("Dragging");
            */
			}
				
			public void mouseMoved(MouseEvent e) {
				handleMouseMoved(e.getX(), e.getY());
			}
		});
	}
	
	private InputStream getResource(String ref) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ref);
		if (in != null) {
			return in;
		}
		
		return new FileInputStream(ref);
	}
	
	private void handleMouseMoved(int x, int y) {
		x -= 0;
		y -= 0;
		x /= 64;
		y /= 64;
		
		if ((x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles())) {
			return;
		}
		
	}
	
	private void handleMousePressed(int x, int y) {
		x -= 0;
		y -= 0;
		x /= 64;
		y /= 64;
			
		if (map.getTerrain(x, y) != map.TREES) {
				map.fillArea(x,y,1,1,map.TREES);
		}
		
		/*
		if (map.getTerrain(x, y) == map.TREES) {
			map.fillArea(x,y,1,1,map.GRASS);
		}
		*/
		repaint(0);
	}
	
	
	public void paint(Graphics graphics) {	
		// create an offscreen buffer to render the map

		if (buffer == null) {
			buffer = new BufferedImage(1200, 1200, BufferedImage.TYPE_INT_RGB);			
		}
		Graphics g = buffer.getGraphics();
		
		g.clearRect(0,0,1200,1200);
		//g.translate(50, 50);
		g.translate(0, 0);
		
		// cycle through the tiles in the map drawing the appropriate

		// image for the terrain and units where appropriate

		for (int x=0;x<map.getWidthInTiles();x++) {
			for (int y=0;y<map.getHeightInTiles();y++) {
				g.drawImage(tiles[map.getTerrain(x, y)],x*64,y*64,null);
				g.setColor(Color.black);
				g.drawRect(x*64, y*64, 64, 64);
			}
		}

		graphics.drawImage(buffer, 0, 0, null);
	}

	public int getMapHeight() {
		// TODO Auto-generated method stub
		return map.HEIGHT;
	}
	
	public int getMapWidth() {
		return map.WIDTH;
	}
	
	public void saveMap(String string) throws IOException{
		StringBuilder builder = new StringBuilder();
		for (int x=0;x<map.getWidthInTiles();x++) {
			for (int y=0;y<map.getHeightInTiles();y++) {
				builder.append(map.getTerrain()[x][y]+"");//append to the output string
			      if(y<map.getHeightInTiles() - 1)//if this is not the last row element
			         builder.append(",");//the
			}
			builder.append("\n");//append new line at the end of the row
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(string));
		writer.write(builder.toString());//save the string representation of the board
		writer.close();
		
	}

}
