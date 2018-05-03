package edu.nyu.cs9053.homework4.hierarchy;

public class Snowboarder extends WinterSportPlayer {

	private final String style;

	public Snowboarder(String name, int age, String style) {
		super(name, age);
		this.style = style;
	}

	public String getStyle() {
		return style;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Snowboarder that = (Snowboarder) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& style == that.getStyle();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + style == null ? 0 : style.hashCode();
		return result;
	}
	
}