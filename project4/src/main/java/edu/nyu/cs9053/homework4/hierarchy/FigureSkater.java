package edu.nyu.cs9053.homework4.hierarchy;

public class FigureSkater extends IceSkater {
	private final String discipline;

	public FigureSkater(String name, int age, int skateSize, String discipline) {
		super(name, age, skateSize);
		this.discipline = discipline;
	}

	public String getDiscipline() {
		return discipline;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		FigureSkater that = (FigureSkater) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& discipline == that.getDiscipline();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + discipline == null ? 0 : discipline.hashCode();
		return result;
	}

}