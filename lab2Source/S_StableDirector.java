package lab2Source;

public class S_StableDirector {

	private S_StableBuilder sb;

	public void setStableBuilder(S_StableBuilder sb) {
		this.sb = sb;
	}

	public Stable getStable() {
		return sb.getStable();
	}

	public void constructStable() {
		sb.buildStable();
		sb.buildRoom(0, 0);
		sb.buildRoom(1, 0);
		sb.buildRoom(2, 0);
		sb.buildRoom(0, 2);
		sb.buildRoom(1, 2);

		sb.buildRoom(0, 1);
		sb.buildRoom(1, 1);
		sb.buildRoom(2, 1);

		sb.buildRoom(3, 1);
		sb.buildRoom(2, 2);

		sb.buildSides(0, 0, 0, 1, BoxDoor.class.getSimpleName());
		sb.buildSides(1, 0, 1, 1, BoxDoor.class.getSimpleName());
		sb.buildSides(2, 0, 2, 1, BoxDoor.class.getSimpleName());
		sb.buildSides(0, 2, 0, 1, BoxDoor.class.getSimpleName());
		sb.buildSides(1, 2, 1, 1, BoxDoor.class.getSimpleName());

		sb.buildSides(3, 1, 2, 1, Door.class.getSimpleName());
		sb.buildSides(2, 2, 2, 1, Door.class.getSimpleName());

		sb.buildSides(0, 1, 1, 1, "");
		sb.buildSides(1, 1, 2, 1, "");

	}
}
