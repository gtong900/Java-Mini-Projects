package edu.nyu.cs9053.homework4.hierarchy;

public class IceSkater extends WinterSportPlayer {

	private final int skateSize;

	public IceSkater(String name, int age, int skateSize) {
		super(name, age);
		this.skateSize = skateSize;
	}

	public int getSkateSize() {
		return skateSize;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		IceSkater that = (IceSkater) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& skateSize == that.getSkateSize();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + Integer.hashCode(skateSize);
		return result;
	}
	
}