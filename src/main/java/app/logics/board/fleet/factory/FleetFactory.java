package app.logics.board.fleet.factory;

import app.logics.board.fleet.FleetModel;
import app.logics.board.ship.Aircraftcarrier;
import app.logics.board.ship.Battleship;
import app.logics.board.ship.Cruiser;
import app.logics.board.ship.Destroyer;
import app.logics.board.ship.Ship;
import app.logics.board.ship.Submarine;

/**
 * Class implements creation of a fleet. Number of ships with specified quantities
 * @author Artur Vitt
 *
 */
public class FleetFactory {
	private FleetFactory() {}
	public static Ship[] getFleet() {
		int totalShips = FleetModel.getFleetSize();
		int idx = 0;
		Ship[] ships = new Ship[totalShips];
		for(int i = 0; i < FleetModel.AIRCRAFTCARRIER_COUNT;i++) {
			ships[idx++] = new Aircraftcarrier();
		}
		for(int i = 0; i < FleetModel.BATTLESHIP_COUNT;i++) {
			ships[idx++] = new Battleship();
		}
		for(int i = 0; i < FleetModel.CRUISER_COUNT;i++) {
			ships[idx++] = new Cruiser();
		}
		for(int i = 0; i < FleetModel.DESTROYER_COUNT;i++) {
			ships[idx++] = new Destroyer();
		}
		for(int i = 0; i < FleetModel.SUBMARINE_COUNT;i++) {
			ships[idx++] = new Submarine();
		}
		return ships;
	}		
}
