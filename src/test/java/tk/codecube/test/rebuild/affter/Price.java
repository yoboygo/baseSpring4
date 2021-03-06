package tk.codecube.test.rebuild.affter;

public abstract class Price {

	private double baseCharge;
	private double chargePerDay;

	public double getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(double baseCharge) {
		this.baseCharge = baseCharge;
	}

	public double getChargePerDay() {
		return chargePerDay;
	}

	public void setChargePerDay(double chargePerDay) {
		this.chargePerDay = chargePerDay;
	}

	/**
	 * 计算租金
	 * 
	 * @param daysRented
	 * @return
	 */
	public double getRentalCharge(int daysRented) {
		return getChargePerDay() * daysRented;
	}

}
