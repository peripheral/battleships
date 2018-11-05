package app.logics.board.ship;

/**
 * Represents general ship
 * @author Artur Vitt
 *
 */
public abstract class Ship {
	public static enum ORIENTATION{HORIZONTAL,VERTICAL};
	/** Start location of ship on the greed, first value in array
	 * presents X axes and the second value Y axis */
	private int startLocationY = -1;
	private int startLocationX = -1;
	/** Orientation of the ship with respect to the grid */
	private ORIENTATION orientation = ORIENTATION.HORIZONTAL;

	/**
	 * Gets the size of the ship
	 * @return size of the ship
	 */
	public abstract int getSize();

	/**
	 * Returns copy of location, X first, then Y
	 * @return x,y indexes, presenting start count on the grid
	 */
	public int[] getStartLocation() {
		return new int[] {startLocationX,startLocationY};
	}

	/**
	 * Stores index values used to represent the ship on different variables
	 * @param startLocation - indexes
	 */
	public void setStartLocation(int[] startLocation) {
		startLocationX = startLocation[0];
		startLocationY = startLocation[1];
	}

	public ORIENTATION getOrientation() {
		return orientation;
	}

	public void setOrientation(ORIENTATION orientation) {
		this.orientation = orientation;
	}

	public int getStartLocationY() {
		return startLocationY;
	}

	public int getStartLocationX() {
		return startLocationX;
	}

	public void setStartY(int startY) {
		startLocationY = startY;

	}

	public void setStartX(int startX) {
		startLocationX = startX;		
	}

	/**
	 * Tests if value under indexes x,y represent state of this ship
	 * @param x index character dimension
	 * @param y index numerical dimension
	 * @return true if index belongs to ship indexes
	 */
	public boolean contains(int x, int y) {
		switch(orientation) {
		case HORIZONTAL:
			if(isInsideHitBox(startLocationX,startLocationY,getSize(),1,x,y)) {
				return true;
			}else {
				return false;
			}
		case VERTICAL:
			if(isInsideHitBox(startLocationX,startLocationY,1,getSize(),x,y)) {
				return true;
			}else {
				return false;
			}
		default: 
			return false;
		}
	}

	private boolean isInsideHitBox(int startX, int startY, int width, int height
			,int x, int y) {
		return startX <= x && x < startX + width && 
			   startY <= y && y < startY + height;
	}

}
