package lab2Source;

public abstract class S_StableBuilder {
	protected Stable stable;

	public Stable getStable() {
		return stable;
	}

	public void buildStable() {
		stable = Stable.instance();
		stable.S_clear();
	}

	public abstract void buildRoom(int x, int y);

	public abstract void buildDoor();

	public abstract void buildSides(int x1, int y1, int x2, int y2,
			String doorType);
}
