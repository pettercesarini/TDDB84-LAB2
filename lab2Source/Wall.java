/*
 * Created on 2005-mar-25
 *
 */
package lab2Source;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Is class represents a wall in the stable. If someone tries to enter a wall nothing happens 
 * @author Peter Sunnergren
 */
public class Wall extends NullSide {// MapSite {
	private int wallSize;
	
	public Wall (int dir) { 
		super(dir);
	}
	
	/**
	 * If someone tries to enter a wall nothing happends.
	 */
	public void enter(MovingObject p) {
		//nothing happends
	}
	
	/**
	 * Returns a copy of the object.
	 * @return Copy
	 */
	public MapSite cloneMe() {
		return new Wall(this.direction);
	}
	
	/**
	 * Draws the wall.
	 * @param g The graphics to draw into
	 * @param r A room that is used to calculate were to draw the wall.
	 */
	public void paint(Graphics g, Room r) {
		wallSize = (int)Math.round(Room.getSize() * 0.15);
		
		switch (direction) {
		case KeyEvent.VK_UP :
			g.drawImage(Images.instance().getWallUpImage(Room.getSize() - 2*wallSize, wallSize),
					r.getOffsetX() + wallSize, r.getOffsetY(), null);
		break;
		case KeyEvent.VK_RIGHT:
			g.drawImage(Images.instance().getWallRightImage(wallSize, Room.getSize() - 2*wallSize)
					, r.getOffsetX() + Room.getSize() - wallSize, r.getOffsetY() + wallSize, null);
		break;
		case KeyEvent.VK_DOWN:
			g.drawImage(Images.instance().getWallDownImage(Room.getSize() - 2*wallSize, wallSize),
					r.getOffsetX() + wallSize, r.getOffsetY() + Room.getSize() - wallSize, null);
		break;
		case KeyEvent.VK_LEFT:
			g.drawImage(Images.instance().getWallLeftImage(wallSize, Room.getSize() - 2*wallSize),
					r.getOffsetX(), r.getOffsetY() + wallSize, null);
		break;
		}
	}	
}
