package lab2Source;

import java.awt.event.KeyEvent;
import java.util.Vector;

public class S_StableFactory {

	public Vector<Room> createStable() {
		Vector<Room> vector = new Vector<Room>();

		Room box1 = S_getWalledRoom(0, 0);
		Room box2 = S_getWalledRoom(1, 0);
		Room box3 = S_getWalledRoom(2, 0);
		Room box4 = S_getWalledRoom(0, 2);
		Room box5 = S_getWalledRoom(1, 2);

		Room korr1 = S_getWalledRoom(0, 1);
		Room korr2 = S_getWalledRoom(1, 1);
		Room korr3 = S_getWalledRoom(2, 1);

		Room room1 = S_getWalledRoom(3, 1);
		Room room2 = S_getWalledRoom(2, 2);

		BoxDoor boxDoor1 = S_createBoxDoor(box1, korr1);
		BoxDoor boxDoor2 = S_createBoxDoor(box2, korr2);
		BoxDoor boxDoor3 = S_createBoxDoor(box3, korr3);
		BoxDoor boxDoor4 = S_createBoxDoor(box4, korr1);
		BoxDoor boxDoor5 = S_createBoxDoor(box5, korr2);

		Door door1 = S_createDoor(room1, korr3);
		Door door2 = S_createDoor(room2, korr3);

		korr1.setSide(korr2);
		korr2.setSide(korr1);
		korr2.setSide(korr3);
		korr3.setSide(korr2);

		vector.add(box1);
		vector.add(box2);
		vector.add(box3);
		vector.add(box4);
		vector.add(box5);
		vector.add(korr1);
		vector.add(korr2);
		vector.add(korr3);
		vector.add(room1);
		vector.add(room2);
		return vector;
	}

	public static Room S_getWalledRoom(int x, int y) {
		Room room = new Room(x, y);
		room.setSide(new Wall(KeyEvent.VK_UP));
		room.setSide(new Wall(KeyEvent.VK_LEFT));
		room.setSide(new Wall(KeyEvent.VK_RIGHT));
		room.setSide(new Wall(KeyEvent.VK_DOWN));
		return room;
	}

	public static BoxDoor S_createBoxDoor(Room r1, Room r2) {
		BoxDoor door = new BoxDoor(r1, r2);
		r1.setSide(door);
		r2.setSide(door);
		return door;

	}
	

	public static Door S_createDoor(Room r1, Room r2) {
		Door door = new Door(r1, r2);
		r1.setSide(door);
		r2.setSide(door);
		return door;
	}
}
