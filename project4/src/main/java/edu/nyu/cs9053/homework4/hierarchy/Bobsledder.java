package edu.nyu.cs9053.homework4.hierarchy;

public class Bobsledder extends Sledder {
	private final String competeMode;

	public Bobsledder(String name, int age, String sledColor, String competeMode) {
		super(name, age, sledColor);
		this.competeMode = competeMode;
	}

	public String getCompeteMode() {
		return competeMode;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Bobsledder that = (Bobsledder) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& competeMode == that.getCompeteMode();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + competeMode == null ? 0 : competeMode.hashCode();
		return result;
	}

}