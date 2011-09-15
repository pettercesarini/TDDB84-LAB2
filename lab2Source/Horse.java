/*
 * Created on 2005-mar-29
 *
 */
package lab2Source;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;


/**
 * Horse is an automatically moving object.
 * @author Peter Sunnergen
 */

public class Horse extends MovingObject implements Runnable{
	private int halfHorseSize;
	private int horseSpeed = 400;
	private StableApplet applet;
	private static int horseCounter;
	private int horseType;
	
	public Horse(StableApplet a) {
		applet = a;
		horseType = horseCounter;
		horseCounter++;
		do {
			currentX = (int)Math.round(Math.random() * 3);
			currentY = (int)Math.round(Math.random() * 3);
		} while (null == Stable.instance().getRoom(currentX, currentY) || 
				Person.instance().sameAs(currentX, currentY));
	}
	/**
	 * Draws the horse. There is a number if different type of horses.
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {
		halfHorseSize = (int)Math.round(Room.getSize() * 0.30);
		switch (horseType) {
		case 0:
			g.drawImage(Images.instance().getGunnarImage(2*halfHorseSize), Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfHorseSize,
					Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize, null);			
			break;
		case 1:
			g.drawImage(Images.instance().getRayaImage(2*halfHorseSize), Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfHorseSize,
					Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize, null);
			break;
		case 2:
			g.drawImage(Images.instance().getGulliverImage(2*halfHorseSize), Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfHorseSize,
					Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize, null);			
			break;
		default:
			g.setColor(Color.gray);
		g.fillRoundRect(Stable.instance().getRoom(currentX, currentY).getOffsetX()  + Room.getSize() / 2 - halfHorseSize,
				Stable.instance().getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize,
				2*halfHorseSize,
				2*halfHorseSize, 20, 20);
		break;
		}
	}
	
	/**
	 * Makes the horse go one step in a direction or turn to that direction.
	 * @param dir The direction.
	 */
	public void go(int dir) {
		if (facing == dir) {
			Stable.instance().getRoom(currentX, currentY).getSide(dir).enter(this);
		} else {
			facing = dir;
		}
		applet.repaint();
	}
	

	public void operateDoor() {
		MapSite m = Stable.instance().getRoom(currentX, currentY).getSide(facing);
		if (BoxDoor.class == m.getClass()) {
			if (!((BoxDoor)m).isOpen) {
				((BoxDoor)m).operateDoor();
			}
		}
	}
	
	/**
	 * Start a continues loop that moves the horse around.
	 */
	public void run() {
		while (true) {
			try { 
				Thread.sleep((int)(Math.random() * horseSpeed));
			} catch( InterruptedException e ) {
				//System.out.println("Horse: Interrupted Exception caught");
			}
			
			switch ((int)Math.round(Math.random() * 3)) {
			case 0:
				facing = KeyEvent.VK_UP; 
				break;
			case 1:
				facing = KeyEvent.VK_RIGHT; 
				break;
			case 2:
				facing = KeyEvent.VK_DOWN;
				break;
			case 3:
				facing = KeyEvent.VK_LEFT;
				break;
			}
			int X = currentX;
			int Y = currentY;
			
			if (Door.class == Stable.instance().getRoom(X, Y).getSide(facing).getClass()) {
				Door d = (Door)Stable.instance().getRoom(X, Y).getSide(facing);
				
				if (d.isOpen) {
					switch (facing) {
					case KeyEvent.VK_UP:
						Y--;
					break;
					case KeyEvent.VK_RIGHT:
						X++;
					break;
					case KeyEvent.VK_DOWN:
						Y++;
					break;
					case KeyEvent.VK_LEFT:
						X--;
					break;
					}
				}
			} else if (BoxDoor.class == Stable.instance().getRoom(X, Y).getSide(facing).getClass()) {
				BoxDoor b = (BoxDoor)Stable.instance().getRoom(X, Y).getSide(facing);
				
				if (!b.isOpen) {
					b.operateDoor();
				}
				
				switch (facing) {
				case KeyEvent.VK_UP:
					Y--;
				break;
				case KeyEvent.VK_RIGHT:
					X++;
				break;
				case KeyEvent.VK_DOWN:
					Y++;
				break;
				case KeyEvent.VK_LEFT:
					X--;
				break;
				}
				
			} else if (Room.class == Stable.instance().getRoom(X, Y).getSide(facing).getClass()) {
				Room r = (Room)Stable.instance().getRoom(X, Y).getSide(facing);
				X = (r.getOffsetX() - 25) / Room.getSize();
				Y = (r.getOffsetY() - 25) / Room.getSize();
			}	
			
			/**
			 * Only go into if the person does not stand in the new room.
			 */
			if (!Person.instance().sameAs(X, Y)) {
				go(facing);
			}
		}
	}    
}
