package edu.nyu.cs9053.homework7;

public class Litecoin implements Cryptocurrency {
	private final double amount;

	public Litecoin(double amount) {
		this.amount = amount;
	}

	@Override public double getAmount() {
		return this.amount;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Litecoin that = (Litecoin) obj;

		return (this.amount == that.amount);
	}

	@Override public int hashCode() {
		int result = Double.valueOf(this.amount).hashCode();
		return result;
	}
}