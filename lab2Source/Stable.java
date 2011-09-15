/*
 * Created on 2005-mar-23
 *
 */
package lab2Source;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Vector;

/**
 * The root class of the stable. Contains all the rooms.
 * 
 * @author Peter Sunnergren
 */
public class Stable {
	private static Stable instance;
	Vector rooms;

	private Stable() {
		rooms = new Vector();
	}

	/**
	 * Returns the stable instance.
	 * 
	 * @return the singleton instance
	 */
	public static Stable instance() {
		if (null != instance) {
			return instance;
		} else {
			instance = new Stable();
			return instance;
		}
	}

	/**
	 * Adds a room to the stable
	 * 
	 * @param room
	 */
	public void addRoom(Room room) {
		rooms.add(room);
	}

	public void S_addRooms(Vector<Room> rooms) {
		for (Room room : rooms)
			this.rooms.add(room);
	}

	/**
	 * Gets the room at a specific position.
	 * 
	 * @param x
	 *            The rooms X
	 * @param y
	 *            The rooms Y
	 * @return The room or null if no room has specified position.
	 */
	public Room getRoom(int x, int y) {
		Iterator iterator = rooms.iterator();
		while (iterator.hasNext()) {
			Room room = (Room) iterator.next();
			if (room.correctRoom(x, y)) {
				return room;
			}
		}
		return null;
	}

	/**
	 * Draws the stable.
	 * 
	 * @param g
	 *            graphics
	 */
	public void paint(Graphics g) {
		synchronized (rooms) {
			Iterator iterator = rooms.iterator();
			while (iterator.hasNext()) {
				((Room) iterator.next()).paint(g);
			}
		}
	}

	public void S_clear() {
		synchronized (rooms) {
			rooms.clear();
		}

	}
}
