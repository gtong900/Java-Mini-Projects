package edu.nyu.cs9053.homework4.hierarchy;

public class Curler extends WinterSportPlayer {

	private final int curlingExperice;

	public Curler(String name, int age, int curlingExperice) {
		super(name, age);
		this.curlingExperice = curlingExperice;
	}

	public int getCurlingExperice() {
		return curlingExperice;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Curler that = (Curler) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& curlingExperice == that.getCurlingExperice();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + curlingExperice;
		return result;
	}

}