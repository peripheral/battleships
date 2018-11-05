package app.gui.board.grid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import app.logics.board.IBoard;

/**
 * Base class for rendering grids. Draws squared grid of given size and legends for axis. 
 * The size of each division can also be specified. Default size of font equates to 0.3 of square size.
 * @author Artur Vitt
 *
 */
public class Grid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5846465486699271181L;
	/**
	 * Labels for vertical scale
	 */
	private String[] verticalScaleLabels = {"1","2","3","4","5","6","7","8","9","10"}; 
	/**
	 * Labels for horizontal scale
	 */
	private String[] horizontalScaleLabels = { "A","B","C","D","E","F","G","H","I","J"}; 
	/**
	 * Squire size in pixels
	 */
	protected int squareSize = 20;
	/**
	 * Pixel start location for board grid
	 */
	protected Point gridStart = new Point();
	/**
	 * Start location of horizontal text scale
	 */
	protected int[] horizontalTextScaleStart = {squareSize,12};
	/**
	 * Start location of vertical text scale
	 */
	protected int[] verticalTextScaleStart = {2,squareSize+7};
	/**
	 * Grid size in squares, 10 squares in width and height
	 */
	protected int gridSize = 10;
	
	/**
	 * Ratio square to font size
	 */
	protected double ratioSquareToFont = 0.3;
	

	/**
	 * Board To be represented on the grid
	 */
	protected IBoard board;


	

	/**
	 * Performs the drawing of the grid.
	 * @param g -graphical context 
	 */
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font(null,Font.PLAIN,(int)(squareSize-squareSize*ratioSquareToFont)));
		renderVerticalLabels(g,verticalTextScaleStart,verticalScaleLabels,squareSize);
		renderHorizontalLabel(g,horizontalTextScaleStart,horizontalScaleLabels,squareSize);
		renderGridLines(g,gridStart,gridSize,squareSize);	
	}

	/**
	 * Renders grid lines
	 * @param g - graphical context
	 * @param startLoc - left top corner location
	 * @param size - number of squares
	 * @param squareSize - square size
	 */
	private void renderGridLines(Graphics g, Point startLoc, int size, int squareSize) {
		g.setColor(Color.GRAY);
		Point p = (Point)startLoc.clone();
		p.translate(-squareSize, -squareSize);
		renderHorizontalLines(g,p,size+1,squareSize);
		renderVerticalLines(g, p, size+1, squareSize);
	}

	/**
	 * Renders grid lines
	 * @param g - graphical context
	 * @param startLoc - start location
	 * @param size - number of squares, with - height
	 * @param squireSize - size of grid square
	 */
	private void renderVerticalLines(Graphics g, Point startLoc, int size, int squireSize) {
		for (int j = 0; j < size+1; j++) {
			g.drawLine(startLoc.x+j*squireSize, startLoc.y,startLoc.x+j*squireSize,startLoc.y+size*squireSize);
		}		
	}

	/**
	 * Render parallel horizontal lines
	 * @param g - graphical context
	 * @param startLoc -start location
	 * @param size - number of lines +1
	 * @param squareSize - step size, pixels
	 */
	private void renderHorizontalLines(Graphics g, Point startLoc, int size, int squareSize) {
		for (int i = 0; i < size+1; i++) {
			g.drawLine(startLoc.x, startLoc.y+i*squareSize,startLoc.x+size*squareSize,startLoc.y+i*squareSize);
		}			
	}
	

	/**
	 * Renders horizontal scale
	 * @param g - graphical context
	 * @param startLoc - start location, pixels
	 * @param text - text array
	 * @param squireSize - step size
	 */
	private void renderHorizontalLabel(Graphics g, int[] startLoc, String[] text,
			int squireSize) {		
		for (int i = 0; i < text.length; i++) {
			g.drawString(text[i],startLoc[0]+squireSize*i,startLoc[1] );
		}			
	}

	/**
	 * Renders vertical scales
	 * @param g - graphical context
	 * @param startLoc - start location 
	 * @param text - text array
	 * @param squireSize - step size
	 */
	private void renderVerticalLabels(Graphics g,int[] startLoc, String[] text,int squireSize) {		
		for (int i = 0; i < text.length; i++) {
			g.drawString(text[i],startLoc[0],startLoc[1] +squireSize*i);
		}		
	}
	/**
	 * Board to draw on the grid
	 * @param board  logical board
	 */
	public void setBoard(IBoard board) {
		this.board = board;		
	}
	
	/**
	 * Get active board
	 * @return currently used board
	 */
	public IBoard getBoard() {
		return board;		
	}

	/**
	 * Gets approximated id of the squire on grid
	 * @param squareSize - size of square
	 * @param p - pixel location on the Board panel
	 * @return
	 */
	private Point getSquareCoordinates(int squareSize,Point p) {
		return new Point( (int) ((p.x)/squareSize),
				(int) ((p.y)/squareSize));
	}
	
	/**
	 * Gets approximated id of the squire on the grid
	 * @param p - pixel location on panel where grid is
	 * @return point with x,y being indexes of square
	 */
	public Point getSquareCoordinates(Point p) {
		 p.translate(-gridStart.x, -gridStart.y);
		return getSquareCoordinates(squareSize, p);
	}

}
