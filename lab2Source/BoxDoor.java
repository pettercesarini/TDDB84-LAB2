/*
 * Created on 2005-mar-29
 *
 */
package lab2Source;

import java.awt.Graphics;

/**
 * A boxdoor is a connection between two room.
 * 
 * @author Peter Sunnergren
 */

public class BoxDoor extends Door {
	private int doorSize = 14;

	// S_BoxDoor
	public BoxDoor() {
		super();
	}

	public BoxDoor(Room r1, Room r2) {
		super(r1, r2);
	}

	@Override
	public String toString() {
		return "BoxDoor";
	}

	/**
	 * Draws the box door.
	 * 
	 * @param g
	 *            Graphics.
	 * @param r
	 *            The room that draws the box door.
	 */
	public void paint(Graphics g, Room r) {
		if (r.getOffsetY() < otherSideFrom(r).getOffsetY()) {
			// Down
			g.drawImage(Images.instance().getBoxDoorHorizontalImage(
					Room.getSize() - 2 * wallSize, 2 * halfDoorSize, isOpen), r
					.getOffsetX()
					+ wallSize, r.getOffsetY() + Room.getSize() - halfDoorSize,
					null);
		}
		if (r.getOffsetY() > otherSideFrom(r).getOffsetY()) {
			// Up
			g.drawImage(Images.instance().getBoxDoorHorizontalImage(
					Room.getSize() - 2 * wallSize, 2 * halfDoorSize, isOpen), r
					.getOffsetX()
					+ wallSize, r.getOffsetY() - halfDoorSize, null);
		}

		if (r.getOffsetX() < otherSideFrom(r).getOffsetX()) {
			// Left
			g.drawImage(Images.instance().getBoxDoorVerticalImage(
					2 * halfDoorSize, Room.getSize() - 2 * wallSize, isOpen), r
					.getOffsetX()
					+ Room.getSize() - halfDoorSize, r.getOffsetY() + wallSize,
					null);
		}

		if (r.getOffsetX() > otherSideFrom(r).getOffsetX()) {
			// Right
			g.drawImage(Images.instance().getBoxDoorVerticalImage(
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
		return new BoxDoor(this.room1, this.room2);
	}
}
