package app.gui.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.gui.board.grid.Grid;
import app.gui.board.grid.PrimaryGrid;
import app.gui.board.grid.TrackingGrid;
import app.logics.board.EnemyBoard;
import app.logics.board.IBoard;
import app.logics.board.MyBoard;

/**
 * Class provides graphical interface for board. Board panel provides two grids. The smaller grid
 * for tracking own ships and primary grid for tracking enemy grid. Grids are drawn on the panel.
 * Result text line is not rendered manually, but a label placed on the panel.
 * @author Artur Vitt
 *
 */
public class BoardPanel extends JPanel{
	/**
	 * Default width in px
	 */
	public static final int DEFAULT_WIDTH = 550;
	/**
	 * Default height in px
	 */
	public static final int DEFAULT_HEIGHT = 600;
	
	/**
	 * Squire size in pixels
	 */
	private int squareSizeTrackingGrid = 15;
	
	/**
	 * Squire size in pixels
	 */
	private int squareSizePrimaryGrid = 35;
	
	

	/**
	 * Grid size in squares, 10 squares in width and height
	 */
	private int gridSize = 10;
	
	private Point primaryGridStart = new Point(squareSizeTrackingGrid*(gridSize + 1)
													,squareSizeTrackingGrid*(gridSize + 1));
	private Point trackingGridStart = new Point(squareSizeTrackingGrid,squareSizeTrackingGrid);
	
	private String resultOfShoot = " Miss";
	private JLabel result = new JLabel("Result");


	private Grid primaryGrid;
	private Grid trackingGrid;


	/**
	 * Instantiates primary grid and tracking grid(smaller one), sets BorderLayout
	 */
	public BoardPanel() {
		primaryGrid = new PrimaryGrid(primaryGridStart,squareSizePrimaryGrid);
		trackingGrid = new TrackingGrid(trackingGridStart,squareSizeTrackingGrid);
		setLayout(new BorderLayout());
		add(result,BorderLayout.SOUTH);
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		addMouseListener(new MouseListener());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		primaryGrid.draw(g);
		trackingGrid.draw(g);
	}

	/**
	 * Local class implements mouse listener on BoardPanels
	 * @author Walter White
	 *
	 */
	class MouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			Point p = primaryGrid.getSquareCoordinates(e.getPoint());
			result.setText("Result:"+primaryGrid.getBoard().fire(p.x, p.y));
			repaint();
			super.mousePressed(e);
		}		
	}

	/**
	 * Gets approximated id of the squire on grid
	 * @param squareSize  size of square
	 * @param p  pixel location on the Board panel
	 * @return point with index for x,y dimensions
	 */
	public Point getSquareLocation(int squareSize,Point p) {
		return new Point( (int) ((p.getX() - squareSize)/squareSize),
				(int) ((p.getY() - squareSize)/squareSize));
	}

	/**
	 * Sets the logical board for enemyBoard, to be displayed on primary grid
	 * @param enemyBoard - logical enemy board, with placed ships
	 */
	public void setEnemyBoard(IBoard enemyBoard) {
		primaryGrid.setBoard(enemyBoard);		
	}
	
	/**
	 * Sets the logical board for playerBoard, to be displayed on tracking grid
	 * @param myBoard -logical player board
	 */
	public void setMyBoard(IBoard myBoard) {
		trackingGrid.setBoard(myBoard);		
	}
}
