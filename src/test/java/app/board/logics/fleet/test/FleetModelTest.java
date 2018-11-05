package app.board.logics.fleet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.logics.board.fleet.FleetModel;
import app.logics.board.ship.Aircraftcarrier;
import app.logics.board.ship.Battleship;
import app.logics.board.ship.Cruiser;
import app.logics.board.ship.Destroyer;
import app.logics.board.ship.Ship;
import app.logics.board.ship.Submarine;

/**
 * Class defines fleet model. 
 * @author 
 *
 */
public class FleetModelTest {

	
	@BeforeEach
	void init() {
	
	}
	
	@Test
	void testShipCountConstants() {
		int expectedAircrafCarrierCount = 1;
		int expectedBattleshipCount = 1;
		int expectedCruiserCount = 1;
		int expectedDestroyerCount = 2;
		int expectedSubmarineCount = 2;
		assertEquals(expectedAircrafCarrierCount,FleetModel.AIRCRAFTCARRIER_COUNT);
		assertEquals(expectedBattleshipCount,FleetModel.BATTLESHIP_COUNT);
		assertEquals(expectedCruiserCount,FleetModel.CRUISER_COUNT);
		assertEquals(expectedDestroyerCount,FleetModel.DESTROYER_COUNT);
		assertEquals(expectedSubmarineCount,FleetModel.SUBMARINE_COUNT);
	}
	
	@Test
	void testGetFleetSize() {
		int actulFleetsize = FleetModel.getFleetSize();
		int expectedFleetSize = FleetModel.AIRCRAFTCARRIER_COUNT+FleetModel.BATTLESHIP_COUNT+
				FleetModel.CRUISER_COUNT+FleetModel.DESTROYER_COUNT+FleetModel.SUBMARINE_COUNT;
		assertEquals(expectedFleetSize,actulFleetsize);
	}
}
