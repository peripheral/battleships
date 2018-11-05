package app.main;

import app.logics.board.EnemyBoard;
import app.logics.board.IBoard;
import app.logics.board.MyBoard;
import app.logics.board.fleet.factory.FleetFactory;
import app.logics.board.ship.Ship;

/**
 * Class instantiaties enemy and player boards,ships and location of ships
 * @author Artur vitt
 *
 */
public class GameBattleships implements IGameBattleships {
	/**
	 * Interface to player board
	 */
	private IBoard myBoard;
	/**
	 * Interface to enemy board
	 */
	private IBoard enemyBoard;
	
	/**
	 * Initiates player board,ships and randomly places ships
	 * Initiates enemy board,ships and randomly places ships
	 */
	public GameBattleships() {
		myBoard = new MyBoard();
		Ship[] myShips = FleetFactory.getFleet();
		myBoard.setShips(myShips);
		Ship[] enemyShips = FleetFactory.getFleet();
		enemyBoard = new EnemyBoard();
		enemyBoard.setShips(enemyShips);
		enemyBoard.positionShipsRandomly();
		myBoard.positionShipsRandomly();
	}

	@Override
	public IBoard getEnemyBoard() {
		return enemyBoard;
	}

	@Override
	public IBoard getMyBoard() {
		return myBoard;
	}
}
