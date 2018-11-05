package app.main;

import app.logics.board.IBoard;
/**
 * Encapsulating interface
 * @author Artur Vitt
 *
 */
public interface IGameBattleships {
	/**
	 * Gets the logical enemy board
	 * @return board encapsulated in interface
	 */
	IBoard getEnemyBoard();
	/**
	 * Gets the logical player board
	 * @return board encapsulated in interface
	 */
	IBoard getMyBoard();
	
}
