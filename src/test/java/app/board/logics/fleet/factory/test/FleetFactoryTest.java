package app.board.logics.fleet.factory.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.logics.board.fleet.FleetModel;
import app.logics.board.fleet.factory.FleetFactory;
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
public class FleetFactoryTest {
	
	@BeforeEach
	void init() {
		
	}	
	
	
	@Test
	void testGetFleet() {
		Ship[] fleet = FleetFactory.getFleet();
		int aircrafCarrierCount = 0;
		int battleshipCount = 0;
		int cruiserCount = 0;
		int destroyerCount = 0;
		int submarineCount = 0;
		for(Ship ship:fleet) {
			if(ship instanceof Aircraftcarrier) {
				aircrafCarrierCount++;
			}
			if(ship instanceof Battleship) {
				battleshipCount++;
			}
			if(ship instanceof Cruiser) {
				cruiserCount++;
			}
			if(ship instanceof Destroyer) {
				destroyerCount++;
			}
			if(ship instanceof Submarine) {
				submarineCount++;
			}
		}
		
		assertEquals(FleetModel.AIRCRAFTCARRIER_COUNT,aircrafCarrierCount);
		assertEquals(FleetModel.BATTLESHIP_COUNT,battleshipCount);
		assertEquals(FleetModel.CRUISER_COUNT,cruiserCount);
		assertEquals(FleetModel.DESTROYER_COUNT,destroyerCount);
		assertEquals(FleetModel.SUBMARINE_COUNT,submarineCount);
	}
}
