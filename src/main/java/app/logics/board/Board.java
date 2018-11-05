package app.logics.board;

import java.util.Random;

import app.logics.board.ship.Ship;
import app.logics.board.ship.Ship.ORIENTATION;

/**
 * Base class that represents board logics, fire,miss,hit, ship placement. Class provides ship placement mechanism, 
 * and mechanism for tracking sectors fired at and sectors occupied by ships. Contains also specifications 
 * for different messages.
 * @author Artur Vitt
 *
 */
public class Board implements IBoard {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7329724161796655679L;

	/**
	 * Board default width dimension
	 */
	public static final int BOARD_WIDTH_DEFAULT = 10;

	/**
	 * Board default height dimension
	 */
	public static final int BOARD_HEIGHT_DEFAULT = 10;

	/**
	 * Ships that are placed on the tabled
	 */
	private Ship[] ships;

	/**
	 * Keeps track of sectors which ships occupy  
	 */
	private boolean[][] occupiedFields = new boolean[BOARD_HEIGHT_DEFAULT][BOARD_WIDTH_DEFAULT];

	public boolean[][] getOccupiedFields() {
		return occupiedFields;
	}

	public void setOccupiedFields(boolean[][] occupiedFields) {
		this.occupiedFields = occupiedFields;
	}

	/**
	 * Keeps track of attacked fields  
	 */
	private boolean[][] attackedField = new boolean[BOARD_HEIGHT_DEFAULT][BOARD_WIDTH_DEFAULT];
	
	public static final String MESSAGE_MISS = "Miss!";
	public static final String MESSAGE_HIT = "Hit!";
	public static final String MESSAGE_SANK = "You sank my battleship!";
	public static final String MESSAGE_ALL_DESTROYED = "All ships destroyed!";
	public static final String MESSAGE_WRONG_ADDRESS = "Wrong Address, must be 1-10, a-j,A-J";

	public void setShips(Ship[] ships) {
		this.ships = ships;		
	}

	public Ship[] getShips() {
		return ships;
	}

	/**
	 * Randomly position ships on the board
	 */
	public void positionShipsRandomly() {
		for(Ship ship:ships) {
			positionShipRandomly(ship,occupiedFields);
		}
	}
	
	/**
	 * Reuse of random object
	 */
	private Random rm = new Random();
	
	/**
	 * Place ship on the grid. Find place and mark the occupied sectors
	 * @param ship -ship to be positioned
	 * @param b - map of currently occupied squares
	 */
	private void positionShipRandomly(Ship ship, boolean[][] b) {
		setRandomOrientation(ship);
		ship.setStartLocation(new int[] {rm.nextInt(BOARD_WIDTH_DEFAULT),rm.nextInt(BOARD_HEIGHT_DEFAULT)});
		int[] startLocation  = findFreePosition(b, ship);
		ship.setStartLocation(startLocation);
		markOccupiedSectors(b,ship);
	}

	/**
	 * Marks sectors occupied by the ship
	 * @param ship - ship
	 * @param b - two dimensional array of type boolean [col][row]
	 */
	public void markOccupiedSectors(boolean[][] b,Ship ship) {
		switch(ship.getOrientation()) {
		case HORIZONTAL:
			for (int i = 0; i < ship.getSize(); i++) {
				markSector(b,ship.getStartLocationX()+i,ship.getStartLocationY());
			}
			return;
		case VERTICAL:
			for (int i = 0; i < ship.getSize(); i++) {
				markSector(b,ship.getStartLocationX(),ship.getStartLocationY() + i);
			}
			return;
		default:
			return;
		}
	}

	/**
	 * Mark sector by setting
	 * value at index [x][y] to true
	 * @param b - board
	 * @param x - column
	 * @param y - row 
	 */
	private void markSector(boolean[][] b, int x, int y) {
		b[x][y] = true;		
	}

	/**
	 * Find available location for the ship with current orientation
	 * @param b - currently occupied fields
	 * @param ship - ship to be placed
	 * @return - return -1,-1 if not found
	 */
	public int[] findFreePosition(boolean[][] b,Ship ship) {
		int startX = ship.getStartLocation()[0];
		int startY = ship.getStartLocation()[1];
		boolean found = isSpaceAvailable(b, ship);
		if(found) {
			return new int[]{startX,startY};
		}

		/* Iterate over rows and columns  */
		for(int row = 0; row < b.length;row ++) {
			startY = (startY + 1)%b.length;
			ship.setStartY(startY);
			for(int col = 0;col < b[row].length;col ++ ) {
				startX = (startX + 1)%b[row].length;
				ship.setStartX(startX);
				found = isSpaceAvailable(b, ship);
				if(found) {
					return new int[]{startX,startY};
				}
			}

		}

		/* Return negatives if available space not found */
		return new int[]{-1,-1};			
	}


	/**
	 * Tests if the ship can be placed this position
	 * @param board - board description
	 * @param ship ship to be placed(location, orientation,size) 
	 * @return true if space is not occupied, else false
	 */
	public boolean isSpaceAvailable(boolean[][] board,Ship ship) {
		int startX =  ship.getStartLocationX();
		int startY = ship.getStartLocationY();
		int size = ship.getSize();
		ORIENTATION orientation = ship.getOrientation();
		switch(orientation) {
		case HORIZONTAL:
			if(!isWidthinBoardBoundary(startX+size-1,startY,board)) {
				return false;
			}
			for (int i = 0; i < size; i++) {
				if(board[startX+i][startY]) {
					return false;
				}
			}
			return  true;
		case VERTICAL:
			if(!isWidthinBoardBoundary(startX,startY+size-1,board)) {
				return false;
			}
			for (int i = 0; i < size; i++) {
				if(board[startX][startY+i]) {
					return false;
				}
			}
			return  true;
		default:
			break;
		}
		return false;
	}

	private boolean isWidthinBoardBoundary(int endX, int endY, boolean[][] b) {
		return endX < b.length && endY < b[0].length;
	}
	
	/**
	 * Set random orientation on the ship
	 * @param ship object to which orientation will be set
	 */
	public void setRandomOrientation(Ship ship) {
		ship.setOrientation(ORIENTATION.values()[rm.nextInt(ORIENTATION.values().length)]);
	}

	public String fire(int x,int y) {
		if(isWithinBoard(x,y)) {
			CasualtyReport report   = applyShot(this,x,y);
			if(report.allShipsDestroyed) {
				return MESSAGE_ALL_DESTROYED;
			}else {
				return report.toString();
			}			
		}
		return MESSAGE_MISS;
	}

	private boolean isWithinBoard(int x, int y) {		
		return x < BOARD_WIDTH_DEFAULT && y < BOARD_HEIGHT_DEFAULT &&
			   x >= 0  && y >= 0 ;
	}

	public CasualtyReport applyShot(Board board,int x, int y) {
		CasualtyReport report = new CasualtyReport();
		Ship ship = consumeShot(board, x,y);
		String message;
		boolean isDestroyed = false;
		if(ship != null) {
			isDestroyed = isShipDestroyed(board,ship);
		}
		if(ship == null) {
			message = MESSAGE_MISS;
		}else if(!isDestroyed) {
			message = MESSAGE_HIT;
		}else {
			message = MESSAGE_SANK;
		}
		report.setMessage(message);
		if(areAllShipsDestroyed(board)) {
			report.setAllShipsDestroyed(true);
		}
		return report;		
	}

	private boolean areAllShipsDestroyed(Board b) {
		for(Ship ship:b.ships) {
			if(!isShipDestroyed(b, ship)) {
				return false;
			}
		}
		return true;
	}

	private Ship consumeShot(Board board, int x, int y) {
		Ship hitShip = getHitTarget(board,x,y);
		board.attackedField[x][y] = true;
		return hitShip;
	}

	private Ship getHitTarget(Board board, int x, int y) {
		if(board.occupiedFields[x][y] && !board.attackedField[x][y]) {
			return getShip(board,x,y);
		}
		return null;		
	}

	private boolean isShipDestroyed(Board board, Ship ship) {
		int squaresLeft = getLeftSquares(board.attackedField,ship);
		return squaresLeft  == 0;
	}

	public int getLeftSquares(boolean[][] attackedField,Ship ship) {
		int counter = 0;
		switch(ship.getOrientation()) {
		case HORIZONTAL:
			for (int i = 0; i < ship.getSize(); i++) {
				if(!attackedField[ship.getStartLocationX()+i][ship.getStartLocationY()]) {
					counter++;
				}
			}
			return counter;
		case VERTICAL:
			for (int i = 0; i < ship.getSize(); i++) {
				if(!attackedField[ship.getStartLocationX()][ship.getStartLocationY()+i]) {
					counter++;
				}
			}
			return counter;
		default: 
			return 0;
		}
	}

	/**
	 * Get ship under coordinates
	 * @param board
	 * @param x
	 * @param y
	 * @return
	 */
	private Ship getShip(Board board, int x, int y) {
		for(Ship ship:board.ships) {
			if(ship.contains(x,y)) {
				return ship;
			}
		}
		return null;
	}

	class CasualtyReport{
		String message = MESSAGE_MISS; 
		boolean allShipsDestroyed = false;
		public void setMessage(String m) {
			message = m;

		}
		public void setAllShipsDestroyed(boolean b) {
			allShipsDestroyed = b;
		}

		public String toString() {
			return message;
		}

	}

	@Override
	public String fire(int i, char a) {
		int value;
		if('A' <= a && a <= 'J') {
			value = a - 'A';
		}else if('a' <= a && a <= 'j') {
			value = a - 'a';
		}else {
			return MESSAGE_WRONG_ADDRESS;
		}
		return fire(i,value);
	}

	@Override
	public int[][] getBoardState() {
		int[][] state = new int[attackedField.length][];
		for (int row = 0; row < attackedField.length; row++) {
			state[row] = new int[attackedField[row].length];
			for (int col = 0; col < state.length; col++) {
				if( attackedField[row][col] && occupiedFields[row][col]) {
					state[row][col] = 2;
				}else if(attackedField[row][col]) {
					state[row][col] = 1;
				}else {
					state[row][col] = 0;
				}
			}
		}
		return state;
	}

	public void setAttackedFields(boolean[][] attackedFields) {
		this.attackedField = attackedFields;		
	}
}
