/*
 * Created on 2005-mar-21
 *
 */
package lab2Source;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * This is the class where the students should make changes. Does the double
 * buffering of the graphics.
 * 
 * @author Peter Sunnergren
 */

public class StablePanel extends JPanel {
	private static Image offscreen;
	private static Graphics buffer;
	private Dimension dimension;
	private ArrayList horses;

	public StablePanel(StableApplet a) {
		dimension = new Dimension(400, 400);
		setMinimumSize(dimension);
		offscreen = a.createImage((int) dimension.getWidth(), (int) dimension
				.getHeight());
		buffer = offscreen.getGraphics();
		horses = new ArrayList();
	}

	/**
	 * This method draws the stable, the person and the horses.
	 */
	public void paint(Graphics g) {
		Stable.instance().paint(buffer);

		Iterator iterator = horses.iterator();
		while (iterator.hasNext()) {
			Horse h = (Horse) iterator.next();
			h.paint(buffer);
		}

		Person.instance().paint(buffer);

		g.drawImage(offscreen, 0, 0, this);
	}

	/**
	 * This is called when the user presses 'h' and adds another horse.
	 */
	public void addHorse(StableApplet a) {
		Horse horse1 = new Horse(a);
		horses.add(horse1);
		new Thread(horse1).start();
	}

	/**
	 * Constructs the stable using the default implementation. In this method
	 * the students can see how a small stable is constructed in a nonflexible
	 * manner.
	 */
	public void defaultConstruction() {
		// S_ Added code
		Stable.instance().S_clear();

		Room room1 = new Room(0, 0);
		Room room2 = new Room(1, 0);
		Room room3 = new Room(0, 1);
		Room room4 = new Room(1, 1);

		Door door13 = new Door(room1, room3);
		BoxDoor boxDoor24 = new BoxDoor(room2, room4);
		BoxDoor boxDoor34 = new BoxDoor(room3, room4);

		Stable.instance().addRoom(room1);
		Stable.instance().addRoom(room2);
		Stable.instance().addRoom(room4);
		Stable.instance().addRoom(room3);

		room1.setSide(new Wall(KeyEvent.VK_UP));
		room1.setSide(new Wall(KeyEvent.VK_LEFT));

		room2.setSide(new Wall(KeyEvent.VK_UP));
		room2.setSide(new Wall(KeyEvent.VK_RIGHT));

		room3.setSide(new Wall(KeyEvent.VK_DOWN));
		room3.setSide(new Wall(KeyEvent.VK_LEFT));

		room4.setSide(new Wall(KeyEvent.VK_RIGHT));
		room4.setSide(new Wall(KeyEvent.VK_DOWN));

		room1.setSide(room2);
		room2.setSide(room1);

		room1.setSide(door13);
		room3.setSide(door13);

		room2.setSide(boxDoor24);
		room4.setSide(boxDoor24);

		room3.setSide(boxDoor34);
		room4.setSide(boxDoor34);

	}

	/**
	 * Construction is made using an Factory.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void factoryConstruction() {
		Stable.instance().S_clear();
		S_StableFactory factory = new S_StableFactory();
		Stable.instance().S_addRooms(factory.createStable());
	}

	/**
	 * Construction using the Builder pattern.
	 * 
	 */
	public void builderConstruction() {
		S_StableDirector director = new S_StableDirector();
		S_ConcreteStableBuilder builder = new S_ConcreteStableBuilder();

		director.setStableBuilder(builder);
		director.constructStable();

	}

	/**
	 * Construction using prototypes.
	 * 
	 */
	public void prototypeConstruction() {
		S_StablePrototypeFactory prototype = new S_StablePrototypeFactory();
		Room room = new Room(0, 0);
		prototype.addType("room", room);

		BoxDoor boxDoor = new BoxDoor();
		prototype.addType("boxDoor", boxDoor);

		Door door = new Door();
		prototype.addType("door", door);

		System.out.println("" + prototype.getClone("room"));
		System.out.println("" + prototype.getClone("door"));
		System.out.println("" + prototype.getClone("boxDoor"));

	}
}
