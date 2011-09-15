/*
 * Created on 2005-mar-29
 *
 */
package lab2Source;

/**
 * A null side is a side of a room that does nothing. It is the default side of a room and is later replaced.
 * @author Peter Sunnergren
 */
public class NullSide extends MapSite{
	protected int direction;
	
	public NullSide(int direction) {
		this.direction = direction;
	}
	
	/**
	 * Prints a message stating that something is wrong because something is trying to enter a null side.
	 * @param p Entering moving object.
	 */
	public void enter(MovingObject p) {
		System.out.println("Trying to enter a null side");
	}
	
	/**
	 * Gets the direction to the side inside a room.
	 * @param room The room.
	 */
	public int getDirection(Room room) {
		return direction;
	}
	
	/**
	 * Clones the side.
	 * @return A copy of this object.
	 */
	public MapSite cloneMe() {
		return new NullSide(direction);
	}
}
