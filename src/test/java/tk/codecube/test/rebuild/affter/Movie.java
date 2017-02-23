package tk.codecube.test.rebuild.affter;

public class Movie {

	private String title;

	/**
	 * 押金
	 */
	private Price price;
	private MovieType type;
	private String despcriptioin;

	public Movie() {
	}

	public Movie(String title, MovieType type, double baseCharge) {

		this.title = title;
		this.type = type;
		syncPrice(type, baseCharge);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getDespcriptioin() {
		return despcriptioin;
	}

	public void setDespcriptioin(String despcriptioin) {
		this.despcriptioin = despcriptioin;
	}

	public MovieType getType() {
		return this.type;
	}

	public void setType(MovieType type) {
		this.type = type;
		syncPrice(type);
	}

	public double getBaseCharge() {
		return this.price.getBaseCharge();
	}

	/**
	 * 当电影的类型改变的时候同步Price
	 * 
	 * @param type
	 */
	public void syncPrice(MovieType type) {
		switch (type) {
		case NEW:
			this.price = new NewMoviePrice(this.price.getBaseCharge());
			break;
		case CHILDREN:
			this.price = new ChildrenMoviePrice(this.price.getBaseCharge());
			break;
		case REGULAR:
			this.price = new RegularMoviePrice(this.price.getBaseCharge());
			break;
		default:
			break;
		}
	}

	private void syncPrice(MovieType type, double baseCharge) {
		this.price = new Price() {
			@Override
			public void setBaseCharge(double baseCharge) {
				super.setBaseCharge(baseCharge);
			}
		};
		this.price.setBaseCharge(baseCharge);
		syncPrice(type);
	}
}
