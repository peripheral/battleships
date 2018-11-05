package app.gui.board.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
/**
 * Extends base Grid class to represent tracking grid in the game. 
 * @author Artur Vitt
 *
 */
public class TrackingGrid extends Grid{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 419086914314321990L;

	/**
	 * Instantiates grid
	 * @param gridStart start location of the grid top left corner
	 * @param squareSize size of each division in pixels 
	 */
	public TrackingGrid(Point gridStart, int squareSize) {
		this.gridStart = gridStart;
		this.squareSize = squareSize;
	}
	
	/**
	 * Draws board state, using white to represent unaffected fields
	 * yellow - attacked fields
	 * red - squares where ships where attacked
	 * @param g - graphical context
	 * @param startLoc - start location in pixels
	 * @param occupiedFields - board state, multidimensional int array, 0 unaffected, 1 attacked, 2 hit
	 * @param squareSize - square size in pixels
	 */
	private void renderShips(Graphics g,Point startLoc, boolean[][] occupiedFields,int squareSize) {
		if(occupiedFields != null) {
			for (int col = 0; col < occupiedFields.length; col++) {
				for (int row = 0; row < occupiedFields.length; row++) {
					if(occupiedFields[col][row]) {
						g.setColor(Color.RED);
					}else {
						g.setColor(Color.BLUE);
					}
					g.fillRect(startLoc.x+col*squareSize,startLoc.y+row*squareSize,squareSize, squareSize);
				}
			}
		}				
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		renderShips(g, gridStart, board.getOccupiedFields(), squareSize);
	}
}
