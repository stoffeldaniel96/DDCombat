package DDCombat;

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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pathfinding.AStarPathFinder;
import pathfinding.Path;
import pathfinding.PathFinder;
import creature.*;


public class testCombat extends JPanel {
	/**
	 * 
	 */
	private static String testMap = "src/DDCombat/testMap.txt";
	private static final long serialVersionUID = 1L;
	/** The map on which the units will move */
	private static gridMap map = new gridMap(testMap);
	
	/** The path finder we'll use to search our map */
	private PathFinder finder;
	/** The last path found for the current unit */
	private Path path;
	
	/** The list of tile images to render the map */
	private Image[] tiles = new Image[8];
	/** The offscreen buffer used for rendering in the wonder world of Java 2D */
	private Image buffer;
	
	/** The x coordinate of selected unit or -1 if none is selected */
	private int selectedx = -1;
	/** The y coordinate of selected unit or -1 if none is selected */
	private int selectedy = -1;
	
	/** The x coordinate of the target of the last path we searched for - used to cache and prevent constantly re-searching */
	private int lastFindX = -1;
	/** The y coordinate of the target of the last path we searched for - used to cache and prevent constantly re-searching */
	private int lastFindY = -1;
	
	
    /**
	 * Create a new test game for the path finding tutorial
	 */
	public testCombat() {
		try {
			tiles[gridMap.GRASS] = ImageIO.read(getResource("res/grass.png"));
			tiles[gridMap.TREES] = ImageIO.read(getResource("res/trees.png"));
			tiles[gridMap.WATER] = ImageIO.read(getResource("res/water.png"));
			tiles[gridMap.PLAYER] = ImageIO.read(getResource("res/player.png"));
			tiles[gridMap.GOBLIN] = ImageIO.read(getResource("res/goblin.png"));
		} catch (IOException e) {
			System.err.println("Failed to load resources: "+e.getMessage());
			System.exit(0);
		}
		
		finder = new AStarPathFinder(map, 6, true);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handleMousePressed(e.getX(), e.getY());
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
				handleMouseMoved(e.getX(), e.getY());
			}
		});
		/*
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		*/
		setSize(15*64,(16*64)-32);
		//setResizable(false);
		setVisible(true);
	}
	
	/**
	 * Load a resource based on a file reference
	 * 
	 * @param ref The reference to the file to load
	 * @return The stream loaded from either the classpath or file system
	 * @throws IOException Indicates a failure to read the resource
	 */
	private InputStream getResource(String ref) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ref);
		if (in != null) {
			return in;
		}
		
		return new FileInputStream(ref);
	}
	
	/**
	 * Handle the mouse being moved. In this case we want to find a path from the
	 * selected unit to the position the mouse is at
	 * 
	 * @param x The x coordinate of the mouse cursor on the screen
	 * @param y The y coordinate of the mouse cursor on the screen
	 */
	private void handleMouseMoved(int x, int y) {
		x -= 0;
		y -= 0;
		x /= 64;
		y /= 64;
		
		if ((x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles())) {
			return;
		}
		
		if (selectedx != -1) {
			if ((lastFindX != x) || (lastFindY != y)) {
				lastFindX = x;
				lastFindY = y;
				path = finder.findPath(new creature(map.getUnit(selectedx, selectedy)), 
									   selectedx, selectedy, x, y);
				repaint(0);
			}
		}
	}
	/**
	 * Handle the mouse being pressed. If the mouse is over a unit select it. Otherwise we move
	 * the selected unit to the new target (assuming there was a path found)
	 * 
	 * @param x The x coordinate of the mouse cursor on the screen
	 * @param y The y coordinate of the mouse cursor on the screen
	 */
	private void handleMousePressed(int x, int y) {
		x -= 0;
		y -= 0;
		x /= 64;
		y /= 64;
		
		if ((x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles())) {
			return;
		}
		
		if (map.getUnit(x, y) != 0) {
			selectedx = x;
			selectedy = y;
			lastFindX = - 1;
		} else {
			if (selectedx != -1) {
				map.clearVisited();
				path = finder.findPath(new creature(map.getUnit(selectedx, selectedy)), 
						   			   selectedx, selectedy, x, y);
				
				if (path != null) {
					System.out.println("Total Path Cost: " + (path.getLength()-1)*5 + " Feet of Movement");
					path = null;
					int unit = map.getUnit(selectedx, selectedy);
					map.setUnit(selectedx, selectedy, 0);
					map.setUnit(x,y,unit);
					selectedx = x;
					selectedy = y;
					lastFindX = - 1;
				}
				else
				{
					System.out.println("Can't reach " + x + "," + y);
				}
			}
		}
		repaint(0);
	}
	
	/**
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
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
				g.drawImage(tiles[map.getTerrain(x, y)],x*64+2,y*64,null);
				g.setColor(Color.black);
				g.drawRect(x*64, y*64, 64, 64);
				if (map.getUnit(x, y) != 0) {
					g.drawImage(tiles[map.getUnit(x, y)],x*64,y*64,null);
				} else {
					if (path != null) {
						if (path.contains(x, y)) {
							g.setColor(Color.black);
							g.fillRect((x*64)+27, (y*64)+27,10,10);
						}
					}	
				}
			}
		}

		// if a unit is selected then draw a box around it

		if (selectedx != -1) {
			g.setColor(Color.black);
			g.drawRect(selectedx*64, selectedy*64, 63, 63);
			g.drawRect((selectedx*64)-2, (selectedy*64)-2, 67, 67);
			g.setColor(Color.white);
			g.drawRect((selectedx*64)-1, (selectedy*64)-1, 65, 65);
		}
		
		// finally draw the buffer to the real graphics context in one

		// atomic action

		graphics.drawImage(buffer, 0, 0, null);
	}
	
	/**
	 * Entry point to our simple test game
	 * 
	 * @param argv The arguments passed into the game
	 */
	/*
	public static void main(String[] argv) {
		testCombat test = new testCombat();
		
	}
	*/
}
