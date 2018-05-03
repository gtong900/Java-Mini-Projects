package edu.nyu.cs9053.homework4.hierarchy;

public class Luger extends Sledder {
	private final float topSpeed;

	public Luger(String name, int age, String sledColor, float topSpeed) {
		super(name, age, sledColor);
		this.topSpeed = topSpeed;
	}

	public float getTopSpeed() {
		return topSpeed;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Luger that = (Luger) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& topSpeed == that.getTopSpeed();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + Float.hashCode(topSpeed);
		return result;
	}

}