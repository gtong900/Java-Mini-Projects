package edu.nyu.cs9053.homework4.hierarchy;

public class MogulSkier extends Skier {
	private final int turnsCount;

	public MogulSkier(String name, int age, int skiLength, int turnsCount) {
		super(name, age, skiLength);
		this.turnsCount = turnsCount;
	}

	public int getTurnsCount() {
		return turnsCount;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		MogulSkier that = (MogulSkier) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& turnsCount == that.getTurnsCount();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + turnsCount;
		return result;
	}

}