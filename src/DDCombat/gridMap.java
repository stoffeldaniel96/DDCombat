package DDCombat;

import pathfinding.Mover;
import pathfinding.TileBasedMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import creature.*;

public class gridMap implements TileBasedMap {
	/** The map width in tiles */
	public static final int WIDTH = 15;
	/** The map height in tiles */
	public static final int HEIGHT = 15;
	
	/** Indicate grass terrain at a given location */
	public static final int GRASS = 0;
	/** Indicate water terrain at a given location */
	public static final int WATER = 1;
	/** Indicate trees terrain at a given location */
	public static final int TREES = 2;
	
	public static final int PLAYER = 6;
	
	public static final int GOBLIN = 7;
	/** The terrain settings for each tile in the map */
	private int[][] terrain = new int[WIDTH][HEIGHT];
	/** The unit in each tile of the map */
	private int[][] units = new int[WIDTH][HEIGHT];
	/** Indicator if a given tile has been visited during the search */
	private boolean[][] visited = new boolean[WIDTH][HEIGHT];
	
	private String name = "Test Field";
	
	/**
	 * Create a new test map with some default configuration
	 */
	public gridMap() {
	
	}
	
	public gridMap(String filePath) {
		loadMap(filePath);
	}
	/**
	 * Fill an area with a given terrain type
	 * 
	 * @param x The x coordinate to start filling at
	 * @param y The y coordinate to start filling at
	 * @param width The width of the area to fill
	 * @param height The height of the area to fill
	 * @param type The terrain type to fill with
	 */
	public void fillArea(int x, int y, int width, int height, int type) {
		for (int xp=x;xp<x+width;xp++) {
			for (int yp=y;yp<y+height;yp++) {
				getTerrain()[xp][yp] = type;
			}
		}
	}
	
	/**
	 * Clear the array marking which tiles have been visited by the path 
	 * finder.
	 */
	public void clearVisited() {
		for (int x=0;x<getWidthInTiles();x++) {
			for (int y=0;y<getHeightInTiles();y++) {
				visited[x][y] = false;
			}
		}
	}
	
	/**
	 * @see TileBasedMap#visited(int, int)
	 */
	public boolean visited(int x, int y) {
		return visited[x][y];
	}
	
	public String getName() {
		return name;
	}
	/**
	 * Get the terrain at a given location
	 * 
	 * @param x The x coordinate of the terrain tile to retrieve
	 * @param y The y coordinate of the terrain tile to retrieve
	 * @return The terrain tile at the given location
	 */
	public int getTerrain(int x, int y) {
		return terrain[x][y];
	}
	
	/**
	 * Get the unit at a given location
	 * 
	 * @param x The x coordinate of the tile to check for a unit
	 * @param y The y coordinate of the tile to check for a unit
	 * @return The ID of the unit at the given location or 0 if there is no unit 
	 */
	public int getUnit(int x, int y) {
		return getUnits()[x][y];
	}
	
	/**
	 * Set the unit at the given location
	 * 
	 * @param x The x coordinate of the location where the unit should be set
	 * @param y The y coordinate of the location where the unit should be set
	 * @param unit The ID of the unit to be placed on the map, or 0 to clear the unit at the
	 * given location
	 */
	public void setUnit(int x, int y, int unit) {
		getUnits()[x][y] = unit;
	}
	
	/**
	 * @see TileBasedMap#blocked(Mover, int, int)
	 */
	public boolean blocked(Mover mover, int x, int y) {
		// if theres a unit at the location, then it's blocked

		if (getUnit(x,y) != 0) {
			return true;
		}
		
		int unit = ((creature) mover).getType();
		
		if (unit == PLAYER) {
			return terrain[x][y] != GRASS;
		}
		
		if (unit == GOBLIN) {
			return terrain[x][y] != GRASS;
		}
		
		return true;
	}

	/**
	 * @see TileBasedMap#getCost(Mover, int, int, int, int)
	 */
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 5;
	}

	/**
	 * @see TileBasedMap#getHeightInTiles()
	 */
	public int getHeightInTiles() {
		return WIDTH;
	}

	/**
	 * @see TileBasedMap#getWidthInTiles()
	 */
	public int getWidthInTiles() {
		return HEIGHT;
	}

	/**
	 * @see TileBasedMap#pathFinderVisited(int, int)
	 */
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}

	public int[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(int[][] terrain) {
		this.terrain = terrain;
	}
	
	public void loadMap(String filePath)
	{
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));
			 for (int i = 0; i < terrain.length && sc.hasNextLine(); i++) {
                 for (int col = 0; col < terrain.length && sc.hasNextInt(); col++) {
                	 terrain[i][col] = sc.nextInt() ;
                  }
                 sc.nextLine(); // col values populated for this row, time to go to the next line
          }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("No can do");
		}
	}

	public int[][] getUnits() {
		return units;
	}

	public void setUnits(int[][] units) {
		this.units = units;
	}
	
	
}
