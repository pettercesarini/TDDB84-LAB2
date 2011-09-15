/*
 * Created on 2005-mar-29
 *
 */
package lab2Source;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Person is the moving object controlled by the user using the arrow keys.
 * @author Peter Sunnergren
 */
public class Person extends MovingObject {
	private static Person instance;
	private int halfPersonSize;
	
	private Person () {
		do {
			currentX = (int)Math.round(Math.random() * 3);
			currentY = (int)Math.round(Math.random() * 3);
		} while (null == Stable.instance().getRoom(currentX, currentY));
		facing = KeyEvent.VK_UP;		
	}

	/**
	 * Gets the instance of the person
	 * @return The singlton instance.
	 */
	public static Person instance() {
		if (null != instance) {
			return instance;
		} else {
			instance = new Person();
			return instance;
		}
	}
	/**
	 * Draws the person.
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {
		halfPersonSize = (int)Math.round(Room.getSize() * 0.35);
		switch (facing) {
		case KeyEvent.VK_UP:
			g.drawImage(Images.instance().getPersonUpImage(2*halfPersonSize), Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
					Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);			
    		break;
    	case KeyEvent.VK_RIGHT:
			g.drawImage(Images.instance().getPersonRightImage(2*halfPersonSize), Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
					Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);			
    		break;
    	case KeyEvent.VK_DOWN:
			g.drawImage(Images.instance().getPersonDownImage(2*halfPersonSize), Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
					Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);			
    		break;
    	case KeyEvent.VK_LEFT:
			g.drawImage(Images.instance().getPersonLeftImage(2*halfPersonSize), Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
					Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);			
    		break;
		}
	}
	
	/**
	 * Makes the person go one step in a direction or turn to that direction.
	 * @param dir The direction.
	 */
	public void go(int dir) {
		if (facing == dir) {
			Stable.instance().getRoom(currentX, currentY).getSide(dir).enter(this);
		} else {
			facing = dir;
		}
	}
	
	/**
	 * Makes the person operate the door in front of her, if she is not facing a door of any kind nothing happens.
	 */
	public void operateDoor() {
		MapSite m = Stable.instance().getRoom(currentX, currentY).getSide(facing);
		if (Door.class == m.getClass() || BoxDoor.class == m.getClass()) {
			((Door)m).operateDoor();		
		}
	}
}
