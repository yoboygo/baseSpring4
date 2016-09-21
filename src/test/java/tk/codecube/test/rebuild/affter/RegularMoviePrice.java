package tk.codecube.test.rebuild.affter;

public class RegularMoviePrice extends Price {

	public RegularMoviePrice(double baseCharge) {
		setChargePerDay(1.5);
		setMovieType(MovieType.REGULAR);
		setBaseCharge(baseCharge);
	}

}
