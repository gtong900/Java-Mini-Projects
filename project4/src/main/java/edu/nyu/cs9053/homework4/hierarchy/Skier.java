package edu.nyu.cs9053.homework4.hierarchy;

public class Skier extends WinterSportPlayer {

	private final int skiLength;

	public Skier(String name, int age, int skiLength) {
		super(name, age);
		this.skiLength = skiLength;
	}

	public int getSkiLength() {
		return skiLength;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Skier that = (Skier) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& skiLength == that.getSkiLength();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + Integer.hashCode(skiLength);
		return result;
	}

	
}