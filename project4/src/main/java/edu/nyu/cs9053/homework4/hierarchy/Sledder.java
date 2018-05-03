package edu.nyu.cs9053.homework4.hierarchy;

public class Sledder extends WinterSportPlayer {

	private final String sledColor;

	public Sledder(String name, int age, String sledColor) {
		super(name, age);
		this.sledColor = sledColor;
	}

	public String getSledColor() {
		return sledColor;
	}
	
	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Sledder that = (Sledder) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& sledColor == that.getSledColor();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + sledColor == null ? 0 : sledColor.hashCode();
		return result;
	}

}