package edu.nyu.cs9053.homework4.hierarchy;

public class SkeletonPlayer extends Sledder {
	private final float skeletonWeight;

	public SkeletonPlayer(String name, int age, String sledColor, float skeletonWeight) {
		super(name, age, sledColor);
		this.skeletonWeight = skeletonWeight;
	}

	public float getSkeletonWeight() {
		return skeletonWeight;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		SkeletonPlayer that = (SkeletonPlayer) obj;

		return (this.getName() == null? that.getName() == null : this.getName().equals(that.getName()))
			&& this.getAge() == that.getAge()
			&& skeletonWeight == that.getSkeletonWeight();
	}

	@Override public int hashCode() {
		int result = this.getName() == null ? 0 : this.getName().hashCode();
		result = 31 * result + this.getAge();
		result = 31 * result + Float.hashCode(skeletonWeight);
		return result;
	}

}