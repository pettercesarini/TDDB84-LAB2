/*
 * Created on 2005-mar-25
 *
 */
package lab2Source;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * A door is a connection between two room that can be opened or closed.
 * 
 * @author Peter Sunnergren
 */

public class Door extends MapSite {
	protected Room room1;
	protected Room room2;
	protected int halfDoorSize;
	protected int wallSize;
	/**
	 * True if the door is open.
	 */
	public boolean isOpen = false;

	// S_Door
	public Door() {
		halfDoorSize = Room.getSize() / 4;
		wallSize = (int) Math.round(Room.getSize() * 0.15);
	}

	public Door(Room r1, Room r2) {
		halfDoorSize = Room.getSize() / 4;
		wallSize = (int) Math.round(Room.getSize() * 0.15);
		move(r1, r2);
	}

	@Override
	public String toString() {
		return "Door";
	}
	/**
	 * Gets the direction to the door form inside a room.
	 * 
	 * @param r
	 *            The room.
	 */
	public int getDirection(Room r) {
		if (r.getOffsetY() < otherSideFrom(r).getOffsetY()) {
			return KeyEvent.VK_DOWN;
		}
		if (r.getOffsetY() > otherSideFrom(r).getOffsetY()) {
			return KeyEvent.VK_UP;
		}
		if (r.getOffsetX() < otherSideFrom(r).getOffsetX()) {
			return KeyEvent.VK_RIGHT;
		}
		if (r.getOffsetX() > otherSideFrom(r).getOffsetX()) {
			return KeyEvent.VK_LEFT;
		}
		return -1;
	}

	/**
	 * Makes the horse or person enter the door and the room on the other side.
	 */
	public void enter(MovingObject p) {
		if (isOpen) {
			otherSideFrom(Stable.instance().getRoom(p.currentX, p.currentY))
					.enter(p);
		}
	}

	/**
	 * Gets the room on the otherside of the door.
	 * 
	 * @param r
	 *            The room.
	 * @return The room on the otherside.
	 */
	protected Room otherSideFrom(Room r) {
		if (room1 == r) {
			return room2;
		} else {
			return room1;
		}
	}

	/**
	 * Draws the door.
	 * 
	 * @param g
	 *            Graphics.
	 * @param r
	 *            The room that draws the door.
	 */
	public void paint(Graphics g, Room r) {
		if (r.getOffsetY() < otherSideFrom(r).getOffsetY()) {
			// Down
			g.drawImage(Images.instance().getDoorHorizontalImage(
					Room.getSize() - 2 * wallSize, 2 * halfDoorSize, isOpen), r
					.getOffsetX()
					+ wallSize, r.getOffsetY() + Room.getSize() - halfDoorSize,
					null);
		}
		if (r.getOffsetY() > otherSideFrom(r).getOffsetY()) {
			// Up
			g.drawImage(Images.instance().getDoorHorizontalImage(
					Room.getSize() - 2 * wallSize, 2 * halfDoorSize, isOpen), r
					.getOffsetX()
					+ wallSize, r.getOffsetY() - halfDoorSize, null);
		}
		if (r.getOffsetX() < otherSideFrom(r).getOffsetX()) {
			// Left
			g.drawImage(Images.instance().getDoorVerticalImage(
					2 * halfDoorSize, Room.getSize() - 2 * wallSize, isOpen), r
					.getOffsetX()
					+ Room.getSize() - halfDoorSize, r.getOffsetY() + wallSize,
					null);
		}
		if (r.getOffsetX() > otherSideFrom(r).getOffsetX()) {
			// Right
			g.drawImage(Images.instance().getDoorVerticalImage(
					2 * halfDoorSize, Room.getSize() - 2 * wallSize, isOpen), r
					.getOffsetX()
					- halfDoorSize, r.getOffsetY() + wallSize, null);
		}
	}

	/**
	 * Returns a copy of the object.
	 * 
	 * @return Copy
	 */
	public MapSite cloneMe() {
		return new Door(this.room1, this.room2);
	}

	/**
	 * Moves the door to be between two rooms.
	 * 
	 * @param r1
	 *            The first room.
	 * @param r2
	 *            The second room.
	 */
	public void move(Room r1, Room r2) {
		if (null != r1) {
			room1 = r1;
		}
		if (null != r2) {
			room2 = r2;
		}
	}

	/**
	 * Operates the door, if it is closed its opened and if it is open it is
	 * closed.
	 */
	public void operateDoor() {
		if (isOpen) {
			isOpen = false;
		} else {
			isOpen = true;
		}
	}
}
