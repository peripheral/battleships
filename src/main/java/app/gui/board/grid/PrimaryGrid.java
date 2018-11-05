package app.gui.board.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Extends base Grid class to represent primary grid in game. 
 * @author Artur Vitt
 *
 */
public class PrimaryGrid extends Grid {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1012031768714672363L;

	/**
	 * Initiates grid with its location and size of single square on grid
	 * @param gridStart start location for the grid
	 * @param squareSize size of the division
	 */
	public PrimaryGrid(Point gridStart,int squareSize) {
		this.gridStart = gridStart;
		this.squareSize = squareSize;
		verticalTextScaleStart[0] =verticalTextScaleStart[0]+ gridStart.x-30;
		verticalTextScaleStart[1] =verticalTextScaleStart[1]+ gridStart.y;
		horizontalTextScaleStart[0] =horizontalTextScaleStart[0]+ gridStart.x-squareSize/9;
		horizontalTextScaleStart[1] =horizontalTextScaleStart[1]+ gridStart.y-squareSize/2;
	}
	
	/**
	 * Draws board state, using white to represent unaffected fields
	 * yellow - attacked fields
	 * red - squares where ships where attacked
	 * @param g - graphical context
	 * @param startLoc  start location in pixels
	 * @param boardState  board state, multidimensional int array, 0 unaffected, 1 attacked, 2 hit
	 * @param squareSize  square size in pixels
	 */
	private void renderBoardState(Graphics g,Point startLoc, int[][] boardState,int squareSize) {
		if(boardState != null) {
			for (int col = 0; col < boardState.length; col++) {
				for (int row = 0; row < boardState.length; row++) {
					switch(boardState[col][row] ) {
					case 0:
						g.setColor(Color.BLUE);
						break;
					case 1:
						g.setColor(Color.WHITE);
						break;
					case 2:
						g.setColor(Color.RED);
						break;
					default:
						g.setColor(Color.BLUE);
						break;
					}
					g.fillRect(startLoc.x+col*squareSize,startLoc.y+row*squareSize,squareSize, squareSize);
				}
			}
		}				
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		renderBoardState(g, gridStart, board.getBoardState(), squareSize);
	}
}
