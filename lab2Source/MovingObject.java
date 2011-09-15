/*
 * Created on 2005-mar-29
 *
 */
package lab2Source;

import java.awt.Graphics;

/**
 * The abstract super class to all object that can move around in the stable.
 * @author Peter Sunnergren
 */
public abstract class MovingObject {
	/**
	 * The current X coordinate in the same coordinat system as the rooms.
	 */
	protected int currentX;
	/**
	 * The current Y coordinate.
	 */
	protected int currentY; 
	/**
	 * The direction the moving object is facing.
	 */
	protected int facing;
	
	/**
	 * Draws the moving object.
	 * @param g
	 */
	public abstract void paint(Graphics g );
	
	/**
	 * Makes the moving object go in a direction.
	 * @param dir The direction.
	 */
	public abstract void go(int dir);
	
	/**
	 * Tells the moving object to operate the door in front of it. 
	 */
	public abstract void operateDoor();
	
	/**
	 * Determins if this movingobject has the same coordinates as another one.
	 * @param x Other x coordinate.
	 * @param y Other y coordinate.
	 * @return True if they are on the same place false otherwise.
	 */
	public boolean sameAs(int x, int y) {
		return ((x == currentX) && (y == currentY));
	}
}
