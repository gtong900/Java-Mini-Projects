package edu.nyu.cs9053.homework4.hierarchy;

public class SpeedSkater extends IceSkater {
	private final boolean isShortTrack;

	public SpeedSkater(String name, int age, int skateSize, boolean isShortTrack) {
		super(name, age, skateSize);
		this.isShortTrack = isShortTrack;
	}

	public boolean isShortTrack() {
		return isShortTrack;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		SpeedSkater that = (SpeedSkater) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& isShortTrack == that.isShortTrack();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + Boolean.hashCode(isShortTrack);
		return result;
	}

}