package app.logics.board;

import java.io.Serializable;

import app.logics.board.ship.Ship;

/**
 * An interface that represents Board with ships
 * @author Artur Vitt
 *
 */
public interface IBoard extends Serializable{
	/**
	 * Add ships to board
	 * @param ships to be placed on board
	 */
	void setShips(Ship[] ships);
	
	/**'
	 * Position ships, randomly
	 */
	void positionShipsRandomly();
	
	/**
	 * Performs action of shooting, returns message such as "Miss", 
	 * "Hit", "You sank my battleship" ,"All ships destroyed", "Wrong Address, must be 1-10, a-j,A-J"
	 * @param i index for numerical dimension
	 * @param a index for character dimension
	 * @return - result of attack
	 */
	String fire(int i, char a);
	
	/**
	 * Performs action of shooting, returns message such as "Miss", 
	 * "Hit", "You sank my battleship" ,"All ships destroyed", "Wrong Address, must be 1-10, a-j,A-J".
	 * @param i index for numerical dimension
	 * @param a index for character dimension
	 * @return - result of attack
	 */
	String fire(int i, int a);
	
	/**
	 * Returns table state, value 1 represents attacked squires, 
	 * value 2 successful hit, value 0 unaffected sector
	 * @return two dimensional array with board state description
	 */
	int[][] getBoardState();
	
	/**
	 * Returns returns 2 dimensional boolean array presenting ship placement 
	 * in format[cols][rows]
	 * @return  two dimensional array with board state description
	 */
	boolean[][] getOccupiedFields();
}
