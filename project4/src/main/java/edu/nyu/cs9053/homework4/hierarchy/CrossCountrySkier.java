package edu.nyu.cs9053.homework4.hierarchy;

public class CrossCountrySkier extends Skier {
	private final String surfaceType;

	public CrossCountrySkier(String name, int age, int skiLength, String surfaceType) {
		super(name, age, skiLength);
		this.surfaceType = surfaceType;
	}

	public String getSurfaceType() {
		return surfaceType;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		CrossCountrySkier that = (CrossCountrySkier) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& surfaceType == that.getSurfaceType();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + surfaceType == null ? 0 : surfaceType.hashCode();
		return result;
	}

}