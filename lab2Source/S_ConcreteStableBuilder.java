package lab2Source;

import java.security.InvalidParameterException;

public class S_ConcreteStableBuilder extends S_StableBuilder {

	@Override
	public void buildDoor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildRoom(int x, int y) {

		stable.addRoom(S_StableFactory.S_getWalledRoom(x, y));

	}

	@Override
	public void buildSides(int x1, int y1, int x2, int y2, String doorType) {
		Room r1 = stable.getRoom(x1, y1);
		Room r2 = stable.getRoom(x2, y2);

		if (r1 == null || r2 == null)
			throw new InvalidParameterException("Rooms are null");

		if (doorType.equalsIgnoreCase(Door.class.getSimpleName())) {
			S_StableFactory.S_createDoor(r1, r2);
		} else if (doorType.equalsIgnoreCase(BoxDoor.class.getSimpleName())) {
			S_StableFactory.S_createBoxDoor(r1, r2);
		} else if (doorType.equalsIgnoreCase("")) {
			r1.setSide(r2);
			r2.setSide(r1);
		} else {
			throw new InvalidParameterException(
					"builderSides får fel, incorrect door type");
		}
	}

}
