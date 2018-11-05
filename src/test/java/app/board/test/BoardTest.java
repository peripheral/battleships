package app.board.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.logics.board.Board;
import app.logics.board.fleet.FleetModel;
import app.logics.board.fleet.factory.FleetFactory;
import app.logics.board.ship.Aircraftcarrier;
import app.logics.board.ship.Battleship;
import app.logics.board.ship.Cruiser;
import app.logics.board.ship.Destroyer;
import app.logics.board.ship.Ship;
import app.logics.board.ship.Submarine;
import app.logics.board.ship.Ship.ORIENTATION;

public class BoardTest {
private Board sut;

	
	@BeforeEach
	void init() {
		sut = new Board();
	}	
	
	/**
	 * Test method isSpaceAvailable-
	 * input data: ship size - 5, startY- 0, startX -0, orientation-horizontal
	 * board - 	[false,false,true ,true,false,false ]
				[true ,true ,false,false,false,false]
				[true,false,false ,false,false,true]
		true define if space is taken, false -space is available
		method should return false - slots (0-5,0) are not free
	 */
	@Test
	void testIsSpaceAvailableShouldReturnFalse() {
		int startX = 0;
		int startY = 0;
		boolean[][] board = {{false,false,true,true,false,false},
							{true,true,false,false,false,false},
							{true,false,false,false,false,false}};
		ORIENTATION orientation = ORIENTATION.HORIZONTAL;
		Ship ship = new Aircraftcarrier();
		ship.setStartLocation(new int[] {startX,startY});
		ship.setOrientation(orientation);
		boolean actual = sut.isSpaceAvailable(board, ship);
		assertFalse(actual);
	}
	
	/**
	 * Test method isSpaceAvailable-
	 * input data: ship size - 5, startY- 2, startX - 1, orientation-horizontal
	 * board - 	[false,false,true ,true,false,false ]
				[true ,true ,false,false,false,false]
				[true ,false,false,false,false,false]
		true define if space is taken, false -space is available
		method should return false - slots (1-4,2) are free
	 */
	@Test
	void testIsSpaceAvailableShouldReturnTrue() {
		int startX = 2;
		int startY = 1;
		boolean[][] board = {{false,false,true,true,false,false},
							{true,true,false,false,false,false},
							{true,false,false,false,false,false}};
		ORIENTATION orientation = ORIENTATION.VERTICAL;
		Ship ship = new Aircraftcarrier();
		ship.setStartLocation(new int[] {startX,startY});
		ship.setOrientation(orientation);
		boolean actual = sut.isSpaceAvailable(board, ship);
		assertTrue(actual);
	}
	
	/**
	 * Test method isSpaceAvailable-
	 * input data: ship size - 5, startY- 3, startX - 1, orientation-horizontal
	 * board - 	[false,false,true ,true ]
				[true ,true ,false,false]
				[true,false,false ,false]
		true define if space is taken, false -space is available
		method should return false - slots (1-4,3) are free
	 */
	@Test
	void testIsSpaceAvailableShouldReturnFalseOutofbounds() {
		int startX = 1;
		int startY = 3;
		boolean[][] board = {{false,false,true,true},
							{true,true,false,false},
							{true,false,false,false}};
		ORIENTATION orientation = ORIENTATION.HORIZONTAL;
		Ship ship = new Aircraftcarrier();
		ship.setStartLocation(new int[] {startX,startY});
		ship.setOrientation(orientation);
		boolean actual = sut.isSpaceAvailable(board, ship);
		assertFalse(actual);
	}
	
	/**
	 * Test method selectPosition -
	 * input data: ship size - 5, startY- 3, startX - 1, orientation-vertical
	 * board - 	[false,false ,true ,true , false]
				[false,false ,false,false, false]
				[true ,false ,false,false, false]
		true define if space is taken, false -space is available
		method should return false - slots (1-4,3) are free
	 */
	@Test
	void testSelectPositionShouldReturn1_0() {
		int startX = 1;
		int startY = 3;
		boolean[][] board = {{false,false,true ,true ,false },
							{ false,false,false,false,false },
							{ true ,false,false,false,false }};
		ORIENTATION orientation = ORIENTATION.VERTICAL;
		Ship ship = new Aircraftcarrier();
		ship.setStartLocation(new int[] {startX,startY});
		ship.setOrientation(orientation);
		int[] expecteds = {1,0};
		int[] actuals = sut.findFreePosition(board, ship);
		assertArrayEquals(expecteds, actuals);
	}
	
	/**
	 * Test method selectPosition -
	 * input data: ship size - 5, startY- 3, startX - 1, orientation-vertical
	 * board - 	[false,false,true ,true ]
				[true ,true ,false,false]
				[true,false,false ,false]
		true define if space is taken, false -space is available
		method should return false - slots (1-4,3) are free
	 */
	@Test
	void testSelectPositionReturnsMinus1Minus1() {
		int startX = 1;
		int startY = 3;
		boolean[][] board = {{false,false,true ,true ,false },
							{ false,false,true,false,false },
							{ true ,false,false,false,false }};
		ORIENTATION orientation = ORIENTATION.VERTICAL;
		Ship ship = new Aircraftcarrier();
		ship.setStartLocation(new int[] {startX,startY});
		ship.setOrientation(orientation);
		int[] expecteds = {-1,-1};
		int[] actuals = sut.findFreePosition(board, ship);
		assertArrayEquals(expecteds, actuals);
	}
	
	/**
	 * Test method fire(i,i) should return hit
	 */
	@Test
	void testFireShouldReturnHit() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 2;
		int shipLocationY = 2;
		Ship[] ships = FleetFactory.getFleet();
		sut.setShips(ships);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[idx]);
		sut.setOccupiedFields(occupiedFields);
		String expecteds = Board.MESSAGE_HIT;
		String actuals = sut.fire(shipLocationX, shipLocationY);
		assertEquals(expecteds, actuals);
	}
	
	/**
	 * Test method fire(2,'A') should return hit
	 */
	@Test
	void testFireWithCharCapShouldReturnHit() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 2;
		int shipLocationY = 0;
		char shipLocation =  'A';
		Ship[] ships = FleetFactory.getFleet();
		sut.setShips(ships);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[idx]);
		sut.setOccupiedFields(occupiedFields);
		String expecteds = Board.MESSAGE_HIT;
		String actuals = sut.fire(shipLocationX, shipLocation);
		assertEquals(expecteds, actuals);
	}
	
	/**
	 * Test method fire(2,'a') should return hit
	 */
	@Test
	void testFireWithCharShouldReturnHit() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 2;
		int shipLocationY = 0;
		char shipLocation =  'a';
		Ship[] ships = FleetFactory.getFleet();
		sut.setShips(ships);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[idx]);
		sut.setOccupiedFields(occupiedFields);
		String expecteds = Board.MESSAGE_HIT;
		String actuals = sut.fire(shipLocationX, shipLocation);
		assertEquals(expecteds, actuals);
	}
	/**
	 * Test method fire(0,'a') should return "You sank my battleship"
	 */
	@Test
	void testFireWithCharShouldReturnYouSankMyBattleShips() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 0;
		int shipLocationY = 0;
		char shipLocationCharY =  'a';
		Ship[] ships = new Ship[2];
		ships[0] = new Submarine();
		/* one extra to not get All ships sunken*/
		ships[1] = new Submarine();
		ships[1].setStartLocation(new int[] {9,9});

		
		
		sut.setShips(ships);
		sut.setOccupiedFields(occupiedFields);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[0]);
		sut.markOccupiedSectors(occupiedFields, ships[1]);
		sut.setOccupiedFields(occupiedFields);
		String expecteds = Board.MESSAGE_SANK;
		String actuals = sut.fire(shipLocationX, shipLocationCharY);
		assertEquals(expecteds, actuals);
	}
	
	/**
	 * Test method fire(0,'a') should return "You sank my battleship"
	 */
	@Test
	void testFireWithCharShouldReturnAllShipsSank() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 0;
		int shipLocationY = 0;
		char shipLocationCharY =  'a';
		Ship[] ships = new Ship[2];
		ships[0] = new Submarine();
		/* one extra to not get All ships sunken*/
		ships[1] = new Submarine();
		ships[1].setStartLocation(new int[] {9,9});

		
		
		sut.setShips(ships);
		sut.setOccupiedFields(occupiedFields);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[0]);
		sut.markOccupiedSectors(occupiedFields, ships[1]);
		sut.setOccupiedFields(occupiedFields);
		sut.fire(9, 9);
		String expecteds = Board.MESSAGE_ALL_DESTROYED;
		String actuals = sut.fire(shipLocationX, shipLocationCharY);
		assertEquals(expecteds, actuals);
	}
	
	/**
	 * Test method fire(0,'a') should return Miss
	 */
	@Test
	void testFireWithCharShouldReturnMiss() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 0;
		int shipLocationY = 1;
		char shipLocationCharY =  'a';
		Ship[] ships = new Ship[2];
		ships[0] = new Submarine();
		/* one extra to not get All ships sunken*/
		ships[1] = new Submarine();
		ships[1].setStartLocation(new int[] {9,9});

		
		
		sut.setShips(ships);
		sut.setOccupiedFields(occupiedFields);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[0]);
		sut.markOccupiedSectors(occupiedFields, ships[1]);
		sut.setOccupiedFields(occupiedFields);
		String expecteds = Board.MESSAGE_MISS;
		String actuals = sut.fire(shipLocationX, shipLocationCharY);
		assertEquals(expecteds, actuals);
	}
	
	/**
	 * Test method fire(0,11) should return Miss
	 */
	@Test
	void testFireWithCharOutsideOfBoundShouldReturnMiss() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 0;
		int shipLocationY = 1;
		int shootLocationY =  11;
		Ship[] ships = new Ship[2];
		ships[0] = new Submarine();
		/* one extra to not get All ships sunken*/
		ships[1] = new Submarine();
		ships[1].setStartLocation(new int[] {9,9});

		
		
		sut.setShips(ships);
		sut.setOccupiedFields(occupiedFields);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[0]);
		sut.markOccupiedSectors(occupiedFields, ships[1]);
		sut.setOccupiedFields(occupiedFields);
		String expecteds = Board.MESSAGE_MISS;
		String actuals = sut.fire(shipLocationX, shootLocationY);
		assertEquals(expecteds, actuals);
	}
	
	/**
	 * Test method fire(2,'*') should return "Wrong Address, must be 1-10, a-j,A-J"
	 */
	@Test
	void testFireWithCharShouldReturnWrongAddress() {
		boolean[][] occupiedFields = new boolean[10][10];
		int idx = 0;
		int shipLocationX = 2;
		int shipLocationY = 0;
		char shipLocation =  '*';
		Ship[] ships = FleetFactory.getFleet();
		sut.setShips(ships);
		
		ships[idx].setStartX(shipLocationX);
		ships[idx].setStartY(shipLocationY);
		sut.markOccupiedSectors(occupiedFields, ships[idx]);
		sut.setOccupiedFields(occupiedFields);
		String expecteds = Board.MESSAGE_WRONG_ADDRESS;
		String actuals = sut.fire(shipLocationX, shipLocation);
		assertEquals(expecteds, actuals);
	}
	

	/**
	 * Test method get boardState
	 */
	@Test
	void testGetBoardState() {
		boolean[][] occupiedFields = new boolean[10][10];
		boolean[][] attackedFields = new boolean[10][10];
		int[][] expecteds = new int[10][10];
		occupiedFields[0][0] = true;
		attackedFields[0][0] = true;
		expecteds[0][0] = 2;
		occupiedFields[0][9] = true;
		attackedFields[0][9] = true;
		expecteds[0][9] = 2;
		occupiedFields[9][9] = true;
		attackedFields[9][9] = true;
		expecteds[9][9] = 2;
		occupiedFields[0][0] = false;
		attackedFields[0][0] = true;
		expecteds[0][0] = 1;
		occupiedFields[0][2] = false;
		attackedFields[0][2] = true;
		expecteds[0][2] = 1;
			
		sut.setOccupiedFields(occupiedFields);
		sut.setAttackedFields(attackedFields);
		int[][] actuals = sut.getBoardState();
		assertArrayEquals(expecteds, actuals);
	}
	
	/**
	 * Test positionShipsRandomly() - compares occupied fields after and 
	 * expected occupied fields
	 */
	@Test
	void testPositionRandomlyMustReturnSameSizeOFOccupiedFields() {
		Ship[] ships = FleetFactory.getFleet();
		sut.setShips(ships);
		sut.positionShipsRandomly();
		int actual = 0;
		boolean[][] fields = sut.getOccupiedFields();
		for (int col = 0; col < fields.length; col++) {
			for (int row = 0; row < fields.length; row++) {
				if(fields[col][row]) {
					actual++;
				}
			}
		}
		int expected = FleetModel.AIRCRAFTCARRIER_COUNT*FleetModel.AIRCRAFTCARRIER_SIZE
				+FleetModel.BATTLESHIP_COUNT*FleetModel.BATTLESHIP_SIZE
				+FleetModel.CRUISER_COUNT*FleetModel.CRUISER_SIZE
				+FleetModel.DESTROYER_COUNT*FleetModel.DESTROYER_SIZE
				+FleetModel.SUBMARINE_COUNT*FleetModel.SUBMARINE_SIZE; 
		assertEquals(expected, actual);
	}
	


	/**
	 * Test getLeftSquares 
	 */
	@Test
	void testGetLeftSquaresShouldReturn2() {
		Ship ship = new Aircraftcarrier();
		ship.setStartX(2);
		ship.setStartY(0);
		ship.setOrientation(ORIENTATION.VERTICAL);
		boolean[][] attackedField = {{false,false,false,false,false,false},
							{false,false,false,false,false,false},
							{false,true,true,false,true,false}};
		int actual = sut.getLeftSquares(attackedField, ship);
		int expected = 2;
		assertEquals(expected, actual);
	}
	
	/**
	 * Test set get ships
	 */
	@Test
	void testSetGetShips() {
		Ship[] expected = FleetFactory.getFleet();
		sut.setShips(expected);
		
		Ship[] actual = sut.getShips();
	
		assertEquals(expected, actual);
	}
}
