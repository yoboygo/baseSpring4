package tk.codecube.test.rebuild.affter;

public class NewMoviePrice extends Price {

	public NewMoviePrice(double baseCharge) {
		setChargePerDay(3.0);
		setMovieType(MovieType.NEW);
		setBaseCharge(baseCharge);
	}

}
