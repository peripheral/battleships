package app.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import app.gui.board.BoardPanel;
import app.main.GameBattleships;
import app.main.IGameBattleships;

/**
 *  Specifies Frame attributes and instantiates board objects, also provides main to start application
 * @author Artur Vitt
 *
 */
public class BattleShipGUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6127827786553906106L;
	/**
	 * Default Frame preferred height
	 */
	public static final int DEFAULT_HEIGHT = 650;
	/**
	 * Default Frame preferred height
	 */
	public static final int DEFAULT_WIDTH = 570;
	/**
	 * Default Frame preferred dimensions
	 */
	public static final Dimension DEFAULT_DIMENSION = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	
	/**
	 * Panel with tracking grid and primary grids
	 */
	private BoardPanel board = new BoardPanel();

 
	/**
	 * Initiates frame with provided game
	 * @param game - logical part of the game
	 */
    public BattleShipGUI(IGameBattleships game) {
    	JFrame frame = new JFrame("Battleship");
    	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(DEFAULT_DIMENSION);
		frame.pack();
		
		frame.setLayout(new FlowLayout());
		frame.add(board);
		
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		board.setEnemyBoard(game.getEnemyBoard());
		board.setMyBoard(game.getMyBoard());
		
	}

	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	GameBattleships game = new GameBattleships();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               new BattleShipGUI(game);
            }
        });
    }
    
    
}
