package app.logics.board.fleet;

/**
 * Class specifies fleet model. Size of a ship, and number per
 * board.
 * @author Artur Vitt
 *
 */
public class FleetModel {
	/**
	 * Permitted number of Aircraft Carriers
	 */
	public static final int AIRCRAFTCARRIER_COUNT = 1;
	/**
	 * Permitted number of Battleships
	 */
	public static final int BATTLESHIP_COUNT = 1;
	/**
	 * Permitted number of Cruisers
	 */
	public static final int CRUISER_COUNT = 1;
	/**
	 * Permitted number of Destroyers
	 */
	public static final int DESTROYER_COUNT = 2;
	/**
	 * Permitted number of Submarines
	 */
	public static final int SUBMARINE_COUNT = 2;

	/**
	 * Size of aircraft carrier 
	 */
	public static final int AIRCRAFTCARRIER_SIZE = 5;
	/**
	 * Size of battleship
	 */
	public static final int BATTLESHIP_SIZE = 4;
	/**
	 * Size of cruiser
	 */
	public static final int CRUISER_SIZE = 3;
	/**
	 * Size of destroyer
	 */
	public static final int DESTROYER_SIZE = 2;
	/**
	 * Size of submarine
	 */
	public static final int SUBMARINE_SIZE = 1;
	
	private FleetModel() {}
	/**
	 * Returns total fleet size
	 * @return - number of ships
	 */
	public static int getFleetSize() {
		return  AIRCRAFTCARRIER_COUNT+BATTLESHIP_COUNT+
				CRUISER_COUNT + DESTROYER_COUNT + SUBMARINE_COUNT;
	}
}
