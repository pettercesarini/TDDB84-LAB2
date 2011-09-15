/*
 * Created on 2005-mar-23
 *
 */
package lab2Source;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A room class that holds the walls or doors.
 * 
 * @author Peter Sunnergren
 */
public class Room extends MapSite {
	private ArrayList sides;
	private static int roomSize = 100; // 50;
	private int offsetX;
	private int offsetY;
	private int x;

	public int S_getX() {
		return x;
	}

	public int S_getY() {
		return y;
	}

	public Room S_setXY(int x, int y) {
		this.x = x;
		this.y = y;
		offsetX = 25 + x * roomSize;
		offsetY = 25 + y * roomSize;
		return this;
	}

	private int y;

	@Override
	public String toString() {
		return "Room";
	}

	/**
	 * Constructor that sets all the walls to be a NullSide.
	 * 
	 * @param x
	 *            The X coordinate of the room.
	 * @param y
	 *            The X coordinate of the room.
	 */
	public Room(int x, int y) {
		this.x = x;
		this.y = y;
		offsetX = 25 + x * roomSize;
		offsetY = 25 + y * roomSize;
		sides = new ArrayList();
		sides.add(new NullSide(KeyEvent.VK_DOWN));
		sides.add(new NullSide(KeyEvent.VK_UP));
		sides.add(new NullSide(KeyEvent.VK_LEFT));
		sides.add(new NullSide(KeyEvent.VK_RIGHT));
	}

	/**
	 * Called when a moving object wants to enter the room and it places the
	 * movingobject in the room.
	 * 
	 * @param p
	 *            The moving object (Person or Horse).
	 */
	public void enter(MovingObject p) {
		p.currentX = x;
		p.currentY = y;
	}

	/**
	 * Gets the direction to this room seen from another room.
	 * 
	 * @param room
	 *            The other room.
	 */
	public int getDirection(Room room) {
		if (room.offsetY < this.offsetY) {
			return KeyEvent.VK_DOWN;
		}
		if (room.offsetY > this.offsetY) {
			return KeyEvent.VK_UP;
		}
		if (room.offsetX > this.offsetX) {
			return KeyEvent.VK_LEFT;
		}
		if (room.offsetX < this.offsetX) {
			return KeyEvent.VK_RIGHT;
		}
		return -1;
	}

	/**
	 * Draws the room.
	 * 
	 * @param g
	 *            graphics
	 */
	public void paint(Graphics g) {
		g.drawImage(Images.instance().getRoomImage(roomSize), offsetX, offsetY,
				null);
		Iterator iter = sides.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			if (Room.class == o.getClass()) {

			} else if (Wall.class == o.getClass()) {
				((Wall) o).paint(g, this);
			} else if (Door.class == o.getClass()) {
				((Door) o).paint(g, this);
			} else if (BoxDoor.class == o.getClass()) {
				((BoxDoor) o).paint(g, this);
			}
		}
	}

	/**
	 * Clones the room.
	 * 
	 * @return a copy of this room.
	 */
	public MapSite cloneMe() {
		Room r = new Room(this.x, this.y);
		Iterator iterator = sides.iterator();
		while (iterator.hasNext()) {
			r.setSide(((MapSite) iterator.next()).cloneMe());
		}
		return r;
	}

	/**
	 * Moves the room.
	 * 
	 * @param x
	 *            New room x coordinate.
	 * @param y
	 *            New room y coordinate.
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		offsetX = 25 + x * roomSize;
		offsetY = 25 + y * roomSize;
	}

	/**
	 * Sets a side of the room. If there already is a side in this direction it
	 * is replaced.
	 * 
	 * @param side
	 *            The new side.
	 */
	public void setSide(MapSite side) {
		Iterator iter = sides.iterator();
		while (iter.hasNext()) {
			MapSite m = (MapSite) iter.next();
			if (side.getDirection(this) == m.getDirection(this)) {
				sides.remove(m);
				iter = sides.iterator();
			}
		}
		sides.add(side);
	}

	/**
	 * Gets the side in a given direction in the room.
	 * 
	 * @param dir
	 *            The direction
	 * @return The side if it exists null otherwise.
	 */
	public MapSite getSide(int dir) {
		MapSite m;
		Iterator iter = sides.iterator();
		while (iter.hasNext()) {
			m = (MapSite) iter.next();
			if (dir == m.getDirection(this)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Gets the x coordinate of the graphical offset. Is used when eg drawing a
	 * Person inside the room
	 * 
	 * @return X offset
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * Gets y offset.
	 * 
	 * @return Y offset
	 */
	public int getOffsetY() {
		return offsetY;
	}

	/**
	 * Gets the room size.
	 * 
	 * @return roomsize
	 */
	public static int getSize() {
		return roomSize;
	}

	/**
	 * Decides wiether or not this is the correct room which means that the room
	 * is placed at the x and y coordinates.
	 * 
	 * @param x
	 *            The searched rooms x coordinate.
	 * @param y
	 *            The searched rooms x coordinate.
	 * @return True if this is the correct room false otherwise.
	 */
	public boolean correctRoom(int x, int y) {
		return (x == this.x) && (y == this.y);
	}
}
